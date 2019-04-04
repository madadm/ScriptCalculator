import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.*;

public class ScriptCalculator {

    public static String resultToFile(String str) {
        String line = "";
        for (int i = 0; i < str.length(); i++) {
            if (i > 0 && i != str.length()) line += "-";
            switch (str.charAt(i)) {
                case '1':
                    line += "one";
                    break;
                case '2':
                    line += "two";
                    break;
                case '3':
                    line += "three";
                    break;
                case '4':
                    line += "four";
                    break;
                case '5':
                    line += "five";
                    break;
                case '6':
                    line += "six";
                    break;
                case '7':
                    line += "seven";
                    break;
                case '8':
                    line += "eight";
                    break;
                case '9':
                    line += "nine";
                    break;
                case '0':
                    line += "zero";
                    break;
            }
        }

        return line;
    }

    public static String result(String[] parts) throws Exception {

        String str = "";
        for (int i = 0; i < parts.length; i++) {
            if (parts[i].contains("-")) {
                str += result(parts[i].split("-"));
                continue;
            }
            try {
                switch (parts[i]) {
                    case ("one"):
                        str += "1";
                        break;
                    case ("two"):
                        str += "2";
                        break;
                    case ("three"):
                        str += "3";
                        break;
                    case ("four"):
                        str += "4";
                        break;
                    case ("five"):
                        str += "5";
                        break;
                    case ("six"):
                        str += "6";
                        break;
                    case ("seven"):
                        str += "7";
                        break;
                    case ("eight"):
                        str += "8";
                        break;
                    case ("nine"):
                        str += "9";
                        break;
                    case ("zero"):
                        str += "0";
                        break;
                    case ("plus"):
                        str += "+";
                        break;
                    case ("minus"):
                        str += "-";
                        break;
                    case ("multiply"):
                        str += "*";
                        break;
                    case ("divide"):
                        str += "/";
                        break;
                    case "open":
                        str += "(";
                        break;
                    case "close":
                        str += ")";
                        break;
                    default:
                        if (!(parts[i].equals("curly") || parts[i].equals("brace") || parts[i].equals("by"))) {
                            throw new Exception("Will fix this later");
                        }
                        break;
                }
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        return str;
    }

    public static String calcString(String str) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        try {
            Object res = engine.eval(str);
            return res.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String line;
        String str = "";
        String[] parts;
        String[] out = new String[10];
        int counter = -1;


        try (BufferedReader in = new BufferedReader(new FileReader("calc.txt"))) {
            while ((line = in.readLine()) != null) {
                counter++;
                parts = line.split(" ");
                out[counter] = "";
                try {
                    str = result(parts);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out[counter] += (line + " = " + resultToFile(calcString(str)));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        if (counter == -1) {
            System.out.println("File is empty");
            return;
        }
        try (BufferedWriter fout = new BufferedWriter(new FileWriter("calc.txt"))) {
            for (int i = 0; i <= counter; i++) {
                fout.write(out[i]);
                fout.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

