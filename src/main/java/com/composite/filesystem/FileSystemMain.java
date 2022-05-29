package com.composite.filesystem;


import java.nio.file.FileAlreadyExistsException;

public class FileSystemMain {

    /**
     * /
     * /wz/
     * /wz/a.txt
     * /wz/b.txt
     * /wz/movies/
     * /wz/movies/c.avi
     * /xzg/
     * /xzg/docs/
     * /xzg/docs/d.txt
     */
    public void view() throws FileAlreadyExistsException {
        FileSystem fileSystem = constructFs();
        print(fileSystem);
    }

    public void removeFile() throws FileAlreadyExistsException {
        FileSystem fileSystem = constructFs();
        Directory docs = FileSystem.getDirectory("/xzg/docs/");
        File dTxt = docs.getFile("d.txt");

        docs.removeSubNode("d.txt");
        System.out.println("remove d.txt file fileSize "+ dTxt.sizeOfFiles());

        print(fileSystem);
    }

    public void addFile() throws FileAlreadyExistsException {
        FileSystem fileSystem = constructFs();
        Directory docs = FileSystem.getDirectory("/xzg/docs/");

        File eTxt = new File("e.txt","this e file");
        docs.addSubNode(eTxt);

        System.out.println("add new file e to the fs fileSize: " + eTxt.sizeOfFiles());
        print(fileSystem);
    }

    public void removeDirectory() throws FileAlreadyExistsException {
        FileSystem fileSystem = constructFs();
        Directory directory = FileSystem.getDirectory("/wz/");
        Directory movies = FileSystem.getDirectory("/wz/movies/");

        directory.removeSubNode("/wz/movies/");
        System.out.printf("remove a dir from fs count: %d size %d%n",movies.getCountOfFiles(),movies.getSizeOfFiles());

        print(fileSystem);
    }

    public void addDirectory() throws FileAlreadyExistsException {
        FileSystem fileSystem = constructFs();
        Directory directory = FileSystem.getDirectory("/wz/");

        Directory dogs = new Directory("/wz/dogs/");

        File dog1 = new File("dog1.txt","this is dog 1");
        File dog2 = new File("dog2.txt","this is dog 2");

        dogs.addSubNode(dog1);
        dogs.addSubNode(dog2);

        directory.addSubNode(dogs);
        System.out.printf("add a dir from fs count: %d size %d%n",dogs.getCountOfFiles(),dogs.getSizeOfFiles());

        print(fileSystem);
    }

    private FileSystem constructFs() throws FileAlreadyExistsException {
        FileSystem.clearFs();

        FileSystem fileSystem = new FileSystem();
        Directory wz = new Directory("/wz/");
        Directory xzg = new Directory("/xzg/");

        fileSystem.addSubNode(wz);
        fileSystem.addSubNode(xzg);

        File aTxt = new File("a.txt","this is File a");
        File bTxt = new File("b.txt","this is File b");

        wz.addSubNode(aTxt);
        wz.addSubNode(bTxt);

        Directory movies = new Directory("/wz/movies/");
        wz.addSubNode(movies);

        File cAvi = new File("c.avi","this is avi type file");
        movies.addSubNode(cAvi);

        Directory docs = new Directory("/xzg/docs/");
        xzg.addSubNode(docs);

        File dTxt = new File("d.txt","this is d file");
        docs.addSubNode(dTxt);

        return fileSystem;
    }

    private void print(FileSystem fileSystem){
        System.out.println(fileSystem.countOfFiles());
        System.out.println(fileSystem.sizeOfFiles());
    }

    public static void main(String[] args) throws FileAlreadyExistsException {
        FileSystemMain fileSystemTest = new FileSystemMain();

        fileSystemTest.view();
        fileSystemTest.addFile();
        fileSystemTest.addDirectory();
        fileSystemTest.removeFile();
        fileSystemTest.removeDirectory();
    }
}
