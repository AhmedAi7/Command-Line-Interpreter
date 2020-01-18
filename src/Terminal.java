import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Ahmed
 */
public class Terminal {

    public void cp(String sourcePath, String destinationPath) throws FileNotFoundException, IOException {
        InputStream is;
        OutputStream os;
        is = new FileInputStream(sourcePath);
        os = new FileOutputStream(destinationPath);
        byte[] buffer = new byte[1024];
        int length;
        while ((length = is.read(buffer)) > 0) {
            os.write(buffer, 0, length);
        }
    }

    public void mkdir(String sourcePath) {
        File directory = new File(sourcePath);
        if (directory.exists())
            System.out.println("Directory is already exist");
        else if (directory.mkdirs())
            System.out.println("Directory is created");
    }

    public void rmdir(String sourcePath) {
        File directory = new File(sourcePath).getAbsoluteFile();
        if (directory.exists()) {
            directory.delete();
            System.out.println("Directory is deleted");
        } else
            System.out.println("Directory is not exist");

    }

    public void pwd() {
        String property = System.getProperty("user.dir");
        System.out.println(property);
    }

    public void cat(ArrayList<String> paths) throws FileNotFoundException, IOException {
        for (int i = 0; i < paths.size(); i++) {
            File directory;
            directory = new File(paths.get(i)).getAbsoluteFile();
            if (directory.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(directory));
                String read;
                while ((read = br.readLine()) != null)
                    System.out.println(read);
            }
            else
                System.out.println(directory + "path is not exist ");
        }
    }

    public void more(String sourcePath) throws FileNotFoundException, IOException {
        File directory;
        directory = new File(sourcePath).getAbsoluteFile();
        if (directory.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(directory));
            String read;
            for (int i = 0; i < 10; i++) {
                System.out.println(br.readLine());
            }

            while ((read = br.readLine()) != null) {
                System.out.println(read);
                Scanner scanner = new Scanner(System.in);
                scanner.nextLine();
            }
        } else
            System.out.println(directory + "path is not exist ");
    }

    public void cd(String sourcePath) {
        File directory;
        directory = new File(sourcePath).getAbsoluteFile();
        if (directory.exists())
            System.setProperty("user.dir", directory.getAbsolutePath());
        else
            System.out.println(directory + "path is not exist ");

    }

    public void Ls() throws FileNotFoundException, IOException {
        File folder = new File(System.getProperty("user.dir"));
        System.out.println("List of Files: ");
        File[] listOfFiles = folder.listFiles();
        for (int i = 0; i < listOfFiles.length; i++) {
            System.out.println(listOfFiles[i].getName());
        }
    }

    public void date() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
    }

    public void clear() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
/*

public void args();
*/

    public void mv(String sourcePath, String destinationPath) throws IOException {
        File source = new File(sourcePath);
        File destination = new File(destinationPath);
        if (source.exists()) {
            Files.move(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);
            System.out.println("File copied");
        } else if (sourcePath.length() == 0 || destinationPath.length() == 0) {
            System.out.println("mv takes two parameters  1: File location \n\t\t\t\t\t\t 2: Destination path ");
        } else {
            System.out.println("File not Exist");
        }
    }

    public void rm(String sourcePath) throws IOException {
        File Fsrc = new File(sourcePath);
        if (Fsrc.exists())
            Files.delete(Fsrc.toPath());
        else if (sourcePath.length() == 0) {
            System.out.println("rm takes one parameter the file directory");
        } else
            System.out.println("File not Exist");
    }

    public void help() {
        System.out.println("cd    : This command changes the current directory to another one.");
        System.out.println("ls    : These programs list each given file or directory name. Directory contents are sorted alphabetically. For ls, files are by default listed in columns, sorted vertically, if the standard output is a terminal; otherwise, they are listed one per line.");
        System.out.println("cp    : If the last argument names an existing directory, cp copies each other given file into a file with the same name in that directory. Otherwise, if only two files are given, it copies the first onto the second. It is an error if the last argument is not a directory and more than two files are given. By default, it does not copy directories.");
        System.out.println("cat   : Concatenate files and print on the standard output.");
        System.out.println("more  : Let us display and scroll down the output in one direction only. You can scroll page by page or line by line.");
        System.out.println("|     : Use pipes â€œ | â€œ to redirect the output of the previous command as in input to another command.");
        System.out.println(">     : Redirect the output to be written to a file using the redirect > create/replace file operator. If the file is not exist, it will be created.");
        System.out.println(">>    : Redirect the output to be written to a file using the redirect >> create/append to file operator. If the file is not exist, it will be created.");
        System.out.println("mkdir : mkdir creates a directory with each given name. By default, the mode of created directories is 0777 minus the bits set in the umask.");
        System.out.println("rmdir : rmdir removes each given empty directory. If any nonoption argument does not refer to an existing empty directory, it is an error.");
        System.out.println("mv    : If the last argument names an existing directory, mv moves each other given file into a file with the same name in that directory. Otherwise, if only two files are given, it moves the first onto the second. It is an error if the last argument is not a directory and more than two files are given. It can move only regular files across file systems. If a destination file is unwritable, the standard input is a tty, and the â€“f or --force option is not given, mv prompts the user for whether to overwrite the file. If the response does not begin with y or Y, the file is skipped.");
        System.out.println("rm    : rm removes each specified file. By default, it does not remove directories. If a file is unwritable, the standard input is a tty, and the -f or --force option is not given, rm prompts the user for whether to remove the file. If the response does not begin with y or Y, the file is skipped. rm can be used to remove directories and its subdirectories and files recursively suing option -r");
        System.out.println("args  : List all command arguments");
        System.out.println("date  : To display the date and time of the system. The format for setting date is [yyyy/MM/dd HH:mm:ss]");
        System.out.println("pwd   : Display current user directory.");
        System.out.println("clear : This command can be called to clear the current terminal screen and it can be redirected to clear the screen of some other terminal.");
        System.out.println("exit : Stop all");

    }

    public void args (String cmd){
        if ( cmd.equals("clear") ){
            System.out.println("[clear] takes no Arguments");
        }
        else if ( cmd.equals("help") ){
            System.out.println("[help] takes no Arguments");
        }
        else if ( cmd.equals("pwd") ){
            System.out.println("[pwd] takes no Arguments");
        }
        else if ( cmd.equals("date") ){
            System.out.println("[date] takes no Arguments");
        }
        else if ( cmd.equals("rm") ){
            System.out.println("[rm] arg1: Source Path");
        }
        else if ( cmd.equals("mv") ){
            System.out.println("[mv] arg1: Source Path , arg2: Destination Path");
        }
        else if ( cmd.equals("cp") ){
            System.out.println("[cp] arg1: Source Path , arg2: Destination Path");
        }
        else if ( cmd.equals("cd") ){
            System.out.println("[mv] arg1: Directory Path");
        }
        else if ( cmd.equals("ls") ){
            System.out.println("[ls] arg1: takes no Arguments");
        }
        else if ( cmd.equals("mkdir") ){
            System.out.println("[mkdir] arg1: Directory Path");
        }
        else if ( cmd.equals("rmdir") ){
            System.out.println("[mkdir] arg1: Directory Path");
        }
        else if ( cmd.equals("more") ){
            System.out.println("[more] arg1: Source Path");
        }
        else if ( cmd.equals(">") ){
            System.out.println("[>] arg1: Source Path");
        }
        else if ( cmd.equals(">>") ){
            System.out.println("[>>] arg1: Source Path");
        }
        else if ( cmd.equals("cat") ){
            System.out.println("[cat] it takes at least one argument , arg; Source Path");
        }
        else if ( cmd.equals("|") ){
            System.out.println("[|] ");
        }
    }
}