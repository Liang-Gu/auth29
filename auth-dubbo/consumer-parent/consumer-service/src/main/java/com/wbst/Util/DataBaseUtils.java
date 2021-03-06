package com.wbst.Util;

import java.io.*;

/**
 * 数据库备份工具类
 */
public class DataBaseUtils {


    /**
     *Java代码实现MySQL数据库导出
     * @param hostIP mySql数据库IP地址
     * @param userName 用户名
     * @param password 密码
     * @param savePath 保存路径
     * @param fileName 保存文件名称
     * @param databaseName 数据库名称
     * @return
     */
    public static  Boolean exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName){
        File file = new File(savePath);
        //如果目录不存在 创建文件夹
        if(!file.exists()){
            file.mkdirs();
        }
        if(!savePath.endsWith(File.separator)){//系统盘符分隔符
            savePath = savePath + File.separator;
        }


        PrintWriter pw = null;
        BufferedReader bufferedReader = null;
        try {
            pw=new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath+fileName),"utf-8"));
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                pw.println(line);
            }
            pw.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return true;
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (pw != null) {
                    pw.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }


    /**
     * @param filepath 数据库备份的脚本路径
     * @param ip IP地址
     * @param database 数据库名称
     * @param userName 用户名
     * @param password 密码
     * @return
     */
    public static boolean recover(String filepath,String ip,String database, String userName,String password) {


        String stmt1 = "mysqladmin -h "+ip+" -u "+userName+" -p"+password+" create "+database;

        String stmt2 = "mysql -h "+ip+" -u "+userName+" -p "+password+" "+database+" < " + filepath;

        String[] cmd = { "cmd", "/c", stmt2 };

        try {
            Runtime.getRuntime().exec(stmt1);
            Runtime.getRuntime().exec(cmd);
            System.out.println("数据已从 " + filepath + " 导入到数据库中");
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }


    public static boolean restore(String url,String user,String password,String databaseName,String path) {
//        System.out.println("mysql -h"+url+" -u"+user+" -p"+password+" --default-character-set=utf8 "
//                + databaseName);
        try {
            Runtime runtime = Runtime.getRuntime();
            Process process = runtime
                    .exec("mysql -h"+url+" -u"+user+" -p"+password+" --default-character-set=utf8 "
                            + databaseName);
            OutputStream outputStream = process.getOutputStream();
            FileInputStream fis = new FileInputStream(path);
            InputStreamReader isr = new InputStreamReader(fis, "utf-8");
            BufferedReader br = new BufferedReader(isr);
            String str = null;
            StringBuffer sb = new StringBuffer();
            while ((str = br.readLine()) != null) {
                sb.append(str + "\r\n");
            }
            str = sb.toString();
            OutputStreamWriter writer = new OutputStreamWriter(outputStream,"utf-8");
            writer.write(str);
            writer.flush();
            if(writer!=null){
                writer.close();
            }

            if(br!=null){
                br.close();
            }
            if(isr!=null){
                isr.close();
            }
            if(fis!=null){
                fis.close();
            }
            if(outputStream!=null){
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
