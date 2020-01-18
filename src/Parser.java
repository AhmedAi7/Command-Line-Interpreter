
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

    ArrayList <String> args=new ArrayList<String>();
    String cmd;

    private static ArrayList<String> splitInput(String subjectString) {
        ArrayList<String> matchList = new ArrayList<>();

        //pattern is : [^\s"]+|"[^"]+"
        Pattern regex = Pattern.compile("[^\\s\"]+|\"[^\"]+\"");
        Matcher regexMatcher = regex.matcher(subjectString);

        while (regexMatcher.find()) {
            matchList.add(regexMatcher.group());
        }
        String buffer;
        for (int i=0 ; i< matchList.size();i++) {
            buffer=matchList.get(i);
            buffer=buffer.replace("\"","");
            matchList.set(i,buffer);
        }
        return matchList;
    }
    public boolean parse(String input) throws NullPointerException
    {
        ArrayList <String> token= new ArrayList<String>();
        ArrayList<String> Command_by2par = new ArrayList<String>();
        ArrayList<String> Command_by1par = new ArrayList<String>();
        ArrayList<String> Command_nopar = new ArrayList<String>();
        Command_by2par.add("$cp");
        Command_by2par.add("$mv");
        //Command_by1par.add("$cat");
        Command_by1par.add("$more");
        Command_by1par.add("$mkdir");
        Command_by1par.add("$rmdir");
        Command_by1par.add("$rm");
        Command_by1par.add("$cd");
        Command_by1par.add("$args");
        Command_nopar.add("$ls");
        Command_nopar.add("$date");
        Command_nopar.add("$pwd");
        Command_nopar.add("$clear");
        Command_nopar.add("$exit");
        Command_nopar.add("$help");
        token= splitInput(input);
        int choice = 3;
        for (int i = 0; i < Command_by2par.size(); i++)
            if (Command_by2par.get(i).equals(token.get(0))) {
                choice = 2;
                break;
            }
        for (int i = 0; i < Command_by1par.size(); i++)
            if (Command_by1par.get(i).equals(token.get(0))) {
                choice = 1;
                break;
            }
        for (int i = 0; i < Command_nopar.size(); i++)
            if (Command_nopar.get(i).equals(token.get(0))) {
                choice = 0;
                break;
            }


        if (token.get(0).equals("$cat") && token.size()>1)
        {
            cmd=token.get(0);

            for (int i=1 ; i<token.size();i++){
                args.add(token.get(i));

            }
            return true;
        }
        if (choice==3)
        {
            System.out.println("Wrong Command");
            return false;
        }

        if (choice == 1 && token.size()==2) {
            cmd=token.get(0);
            System.out.println(token.get(1));
            args.add(token.get(1));
            return true;
        }
        else if (choice == 1 && token.size()!=2){
            System.out.println(token.get(0) + " command should have one parameter");
            return false;
        }
        if (choice == 2 && token.size()==3)
        {
            cmd = token.get(0);
            args.add(token.get(1));
            args.add(token.get(2));
            return true;
        }
        else if (choice == 2 && token.size()!=3){
            System.out.println(token.get(0)+ " command should have two parameter");
            return false;
        }
        if (choice == 0 && token.size()==1) {
            cmd=token.get(0);
            return true;
        }
        else if (choice == 0 && token.size()!=1)
        {
            System.out.println(token.get(0) + " command should not have parameter");
            return false;
        }
        return false;
    }

    public String getCmd() {
        return cmd;
    }

    public ArrayList<String> getArguments() {
        return args;
    }
    void clearargs(){
        args.clear();
    }

}