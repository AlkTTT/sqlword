package com.tools.sqlword;

import com.tools.sqlword.bean.columnBean;
import com.tools.sqlword.bean.tableBean;
import com.tools.sqlword.mapper.tableMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.*;
import java.util.List;


@Component
public class createWord implements CommandLineRunner {

    @Value("${datasouce}")
    private String datasource;

    @Value("${filePath}")
    private String filePath;

    @Autowired
    private tableMapper tableMapper;

    public static createWord createWord;

    @PostConstruct
    public void init(){
        createWord = this;
        createWord.tableMapper = this.tableMapper;
    }

    @Override
    public void run(String... args){
        List<tableBean> tableList = createWord.tableMapper.selectTableByDataSource(datasource);
        StringBuilder sb = new StringBuilder();
        for (tableBean table : tableList){
            List<columnBean> columnList =  createWord.tableMapper.selectColumnByDataSource(datasource,table.getTableName());
            sb.append(table.getTableName()+"   "+table.getTableComment());
            sb.append("\n");
            sb.append("\r\n");
            sb.append("|字段名 | 字段类型 | 默认值 | 注解|");
            sb.append("\r\n");
            sb.append("---- | ---- | ---- | ---- ");
            sb.append("\r\n");
            for (columnBean column : columnList){
                sb.append(column.getColumnName());
                sb.append(" | ");
                sb.append(column.getCoulumType());
                sb.append(" | ");
                sb.append(column.getCoulumDefault());
                sb.append(" | ");
                sb.append(column.getCoulumComment().replace("\r\n"," "));
                sb.append("\r\n");
            }
            sb.append("\r\n");
            sb.append("\r\n");
        }

        String fileName = filePath + datasource + ".md";
        File file = new File(fileName);
        try {
            //此处如果存在文件，不进行更新
            if (!file.exists()){
                file.createNewFile();
                writeFileContent(fileName,sb.toString());
            }
        }catch (Exception e){
            System.out.println(e);
        }

    }

    /**
     * 向文件中写入内容
     * @param filepath 文件路径与名称
     * @param newstr  写入的内容
     * @return
     * @throws IOException
     */
    public static boolean writeFileContent(String filepath,String newstr) throws IOException {
        Boolean bool = false;
        String filein = newstr+"\r\n";//新写入的行，换行
        String temp  = "";

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader br = null;
        FileOutputStream fos  = null;
        PrintWriter pw = null;
        try {
            File file = new File(filepath);//文件路径(包括文件名称)
            //将文件读入输入流
            fis = new FileInputStream(file);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            StringBuffer buffer = new StringBuffer();
            buffer.append(filein);
            fos = new FileOutputStream(file);
            pw = new PrintWriter(fos);
            pw.write(buffer.toString().toCharArray());
            pw.flush();
            bool = true;
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally {
            //不要忘记关闭
            if (pw != null) {
                pw.close();
            }
            if (fos != null) {
                fos.close();
            }
            if (br != null) {
                br.close();
            }
            if (isr != null) {
                isr.close();
            }
            if (fis != null) {
                fis.close();
            }
        }
        return bool;
    }

}
