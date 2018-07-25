package com.imooc.o2o.util;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.geometry.Position;
import net.coobird.thumbnailator.geometry.Positions;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author: Alex
 * @Date: created in 10:26  2018/6/13
 * @Annotation:
 */
public class ImageUtil {

    //获取classpath的绝对路径
    private static String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
    //时间格式化的格式
    private static final SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
    //随机数
    private static final Random r = new Random();

    /**
     * 处理缩略图，并返回新生成图片的相对路径
     * @param
     * @param targetAddr
     * @return
     */
    public static String generateThumbnail(InputStream thumbnailInputStream, String targetAddr , String fileName){

        //获取文件的随机名
        String realFileName = getRandomFileName();
        //获取文件的扩展名
        String extension = getFileExtension(fileName);

        //随机名+扩展名 == 新文件的名字
        makeDirPath(targetAddr);

        //相对路径
        String relativeAddr = targetAddr + realFileName + extension;

        //文件路径 = 根路径+相对路径
        File dest = new File(PathUtil.getImgBasePath() + relativeAddr);
        try{
            Thumbnails.of(thumbnailInputStream).size(200,200)
                    .watermark(Positions.BOTTOM_RIGHT,ImageIO.read(new File(basePath + "/watermark.jpg")),0.25f)
                            .outputQuality(0.8f).toFile(dest);
        }catch (IOException e){
            throw new RuntimeException("创建缩略图失败："+e.toString());
        }
        return relativeAddr;
    }


    /**
     * 创建目标函数所涉及到的目录，即/home/work/zdy/xxx.jpg,那么home work zdy这三个文件夹都要自动创建出来
     * @param targetAddr
     */
    private static void makeDirPath(String targetAddr) {
        String realFileParentPath = PathUtil.getImgBasePath()+targetAddr;
        File dirPath = new File(realFileParentPath);
        if(!dirPath.exists()){
            dirPath.mkdirs();
        }
    }

    /**
     * 获取输入文件流的扩展名 （获取最后一个点号的位置并返回之后的字符）
     * @param
     * @return
     */
    private static String getFileExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    /**
     * 生成随机文件名  当前年月日小时分钟秒钟+五位随机数
     * @return
     */
    public static String getRandomFileName() {
        //获取随机五位数
        int rannum = r.nextInt(89999) + 10000;
        //获取当前时间
        String nowTimeStr = sDateFormat.format(new Date());
        return nowTimeStr+rannum;   //字符串+整形 结果会自动转换成字符串    
    }

    /**
     * storePath可以是文件的路径，也可以是目录的路径
     * 如果storePath是文件路径则删除该文件
     * 如果storePath是文件目录则删除该目录
     * @param storePath
     */
    public static void deleteFileOrPath(String storePath) {
        File fileOrPath = new File(PathUtil.getImgBasePath() + storePath);
        //判断文件路径是否存在
        if (fileOrPath.exists()) {
            //判断文件中是否有文件
            if (fileOrPath.isDirectory()) {
                File files[] = fileOrPath.listFiles();
                for (int i = 0; i < files.length; i++) {
                    //删除文件
                    files[i].delete();
                }
            }
            //删除文件目录
            fileOrPath.delete();
        }
    }

    /*public static void main(String[] args) throws IOException {
        //改变tianhe.jpg的大小 并打上水印
        //String basePath = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        Thumbnails.of(new File("E:\\all\\pictest\\tianhe.jpg")).size(240, 135)
                .watermark(Positions.BOTTOM_RIGHT, ImageIO.read(new File(basePath + "watermark.jpg")), 0.25f)
                .outputQuality(0.8f).toFile("E:\\all\\pictest\\tianhenew.jpg");
    }*/
}
