package com.composite.filesystem;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileAlreadyExistsException;

public class File extends AbstractFileSystemNode{

    private String fileName;

    private byte [] source;

    private File(String fileName,byte [] source) throws FileAlreadyExistsException {
        this.path = fileName;
        this.fileName = fileName;
        this.source = source;
    }

    public File(String fileName,String content) throws FileAlreadyExistsException {
        this(fileName,content.getBytes(StandardCharsets.UTF_8));
    }


    public String getFileName() {
        return fileName;
    }

    @Override
    public int countOfFiles() {
        return 1;
    }

    @Override
    public long sizeOfFiles() {
        return source.length;
    }

    @Override
    public void recalculate(int changeOfCount, long changeOfSize) {
        this.parent.recalculate(0,changeOfSize);
    }

    public void write(byte [] bytes){

        try(ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(source.length)){
            byteArrayOutputStream.write(bytes);
            this.source = byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.recalculate(0,bytes.length);
    }

    public byte [] read(){

        try(ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(source)){
            byte [] bytesSource = new byte[byteArrayInputStream.available()];
            int offset = 0;
            int len;

            while((len = (byteArrayInputStream.read(bytesSource,offset,bytesSource.length - offset))) != -1){
                offset+=len;
            }

            return bytesSource;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
