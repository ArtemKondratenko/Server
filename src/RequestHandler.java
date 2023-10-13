import java.io.IOException;
import java.net.ServerSocket;
import java.util.List;

//ls, cd, touch, mkdir, cat, nano - писать в файл, cd ..
public class RequestHandler {

    public String reqHand(String com) throws IOException {

        Command command = new Command();
        //Проверить нужен ли тут цикл!

        while (true) {
            String[] pasCom = com.split(" ");
            if (pasCom[0].equals("Exit")) {
                break;
            } else if (pasCom[0].equals("ls")) {
                return command.executingLsCommand();
            } else if (pasCom[0].equals("cd")) {
                command.executingCdCommand(com);
                return "OK";
            } else if (pasCom[0].equals("touch")) {
                try {
                    return  command.creatingNewFile(com);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (pasCom[0].equals("mkdir")) {
                try {
                    return  command.creatingNewMkDir(com);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (pasCom[0].equals("cd..")) {
                return  command.stepBack();
            }else if (pasCom[0].equals("pwd")) {
                return  command.executingPWDCommand();
            }
        }
        return null;
    }
}