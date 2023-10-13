import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.ServerSocket;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Command {

    String path = "C:\\Users"; //перевести в статику

    //    Просмотр всех файлов существующих в дериктории
    public String executingLsCommand() throws IOException {
        File file1 = new File(path);
        File[] files = file1.listFiles();
        List<String> lsFiles = new ArrayList<>();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    lsFiles.add(file.getName());
                }
            }
        }
        savingActionsFile("ls");
        return lsFiles.toString();
    }



    public static void savingActionsFile (String action) throws IOException {
        String path = "C:\\Users\\Артем\\LinuxInterface\\log.txt";
        File file1 = new File(path);
        FileWriter writer = new FileWriter(file1,true);
        Date currentDate = new Date();
        String user = System.getProperty("user.name");
        writer.write(System.lineSeparator());
        writer.write(user);
        writer.write(System.lineSeparator());
        writer.write(String.valueOf(currentDate));
        writer.write(System.lineSeparator());
        writer.write(action);
        writer.write(System.lineSeparator());
        writer.close();

    }

    public  String executingPWDCommand() {
        return path;
    }

    //     Переход в определенную дерикторию и получение содержимого дериктории
    public void executingCdCommand(String com) throws IOException {
        String[] pasCom = com.split(" ");
        String root = path + "\\" + pasCom[1];
        File file1 = new File(root);
        path = path + "\\" + pasCom[1];
        savingActionsFile(com);

    }
    //    // создание нового файла
    public String creatingNewFile(String com) throws IOException {
        String[] pasCom = com.split(" ");
        String root = executingPWDCommand();
        String root1 = root + "\\" + pasCom[1];
        File file = new File(root1);
        file.createNewFile();
        File file2 = new File(root);
        List<String> lsFiles = new ArrayList<>();

        for (File file1 : Objects.requireNonNull(file2.listFiles())) {
            lsFiles.add(file1.getName());
        }
        savingActionsFile(com);
        return lsFiles.toString();
    }
    // Создание новой папки
    public String creatingNewMkDir(String com) throws IOException {
        String[] pasCom = com.split(" ");
        String root = executingPWDCommand();
        String root1 = root + "\\" + pasCom[1];
        File file = new File(root1);
        file.mkdir();
        File file2 = new File(root);
        List<String> lsFiles = new ArrayList<>();
        for (File file1 : Objects.requireNonNull(file2.listFiles())) {
            lsFiles.add(file1.getName());
        }
        savingActionsFile(com);
        return lsFiles.toString();
    }
    // Возврат на папку назад
    public String stepBack() throws IOException {

        String root = executingPWDCommand();
        File directory = new File(root);
        String pathWithoutLastDirectory = directory.getParent();
        Path parentPath = Paths.get(pathWithoutLastDirectory);
        Path newPath = parentPath.resolveSibling("..");
        File newDirectory = newPath.toFile();

        List<String> lsFiles = new ArrayList<>();
        for (File file : Objects.requireNonNull(newDirectory.listFiles())) {
            lsFiles.add(file.getName());

        }
        savingActionsFile("cd..");
        return lsFiles.toString();
    }
}
