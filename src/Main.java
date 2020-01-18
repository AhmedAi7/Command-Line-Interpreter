import com.sun.org.apache.xalan.internal.xsltc.runtime.Operators;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Ahmed
 */
public class Main {

    public static boolean Opertator(String [] input,ArrayList <String> file) throws NullPointerException
    {
        if(input[0].contains(">"))
        {
            String[] splits=input[0].split("((?<=>)|(?=>))");
            input[0]=splits[0];
            System.err.println(input[0]);
            for (int i=1;i<splits.length;i++)
            {
                file.add(splits[i]);
            }
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws IOException {
        Parser p=new Parser();
        Terminal T1 = new Terminal();
        boolean loop=true;
        while (loop)
        {
            Scanner input =new Scanner(System.in);
            String x=input.nextLine();
            String[] arrCommands=x.split("\\|");
            List<String> Commands =Arrays.asList(arrCommands);
            String [] CurrCommand=new String [1];
            CurrCommand[0]="";
            ArrayList<String> operoerfile =new ArrayList<String>();
            Boolean Operator;
            PrintStream o = null;
            PrintStream console= null;
            for (int i =0 ; i< Commands.size();i++)
            {
                CurrCommand[0]=Commands.get(i);
                Operator=Main.Opertator(CurrCommand,operoerfile);

                if(Operator)
                {
                    if (operoerfile.get(0).equals(">")&&operoerfile.get(1).equals(">"))
                    {
                        o = new PrintStream(new FileOutputStream(operoerfile.get(operoerfile.size()-1), true));
                    }
                    else if (operoerfile.get(0).equals(">")&&operoerfile.get(1)!=">")
                    {
                        o = new PrintStream(new FileOutputStream(operoerfile.get(operoerfile.size()-1), false));
                    }
                    console = System.out;
                    System.setOut(o);
                }
                if(p.parse(CurrCommand[0]))
                {
                    /*
                    pwd - date - ls - cat - args - help
                    */

                    if(p.getCmd().equals("$cd"))
                    {
                        T1.cd(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$exit"))
                    {
                        loop=false;
                    }
                    else if (p.getCmd().equals("$clear"))
                    {
                        T1.clear();
                    }
                    else if (p.getCmd().equals("$pwd"))
                    {
                        T1.pwd();
                    }

                    else if (p.getCmd().equals("$date"))
                    {
                        T1.date();
                    }
                    else if (p.getCmd().equals("$ls"))
                    {
                        T1.Ls();
                    }
                    else if (p.getCmd().equals("$cp"))
                    {
                        T1.cp(p.getArguments().get(0),p.getArguments().get(1));
                    }
                    else if (p.getCmd().equals("$cat"))
                    {
                        T1.cat(p.getArguments());
                    }
                    else if (p.getCmd().equals("$more"))
                    {
                        T1.more(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$mkdir"))
                    {
                        T1.mkdir(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$rmdir"))
                    {
                        T1.rmdir(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$mv"))
                    {
                        T1.mv(p.getArguments().get(0),p.getArguments().get(1));
                    }

                    else if (p.getCmd().equals("$rm"))
                    {
                        T1.rm(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$args"))
                    {
                        T1.args(p.getArguments().get(0));
                    }
                    else if (p.getCmd().equals("$help"))
                    {

                        T1.help();
                    }
                    p.clearargs();
                }

                if (Operator)
                    System.setOut(console);
            }

        }
    }
}
