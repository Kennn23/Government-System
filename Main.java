package governmentsystem;

import java.io.*;
import java.util.*;

public class Main {

    public static String uType;
    public static String userName;
    
    public static int indexOfAccount;

    private static final int USER_DETAILS = 3;
    public static final int USER_CREATION_LIMIT = 10;
    public static String[][] depEpUserInfo = new String[USER_DETAILS][USER_CREATION_LIMIT];
    public static String[][] doJUserInfo = new String[USER_DETAILS][USER_CREATION_LIMIT];
    public static String[][] pnpUserInfo = new String[USER_DETAILS][USER_CREATION_LIMIT];
    // UserInfo[0] = FirstName
    // UserInfo[1] = LastName
    // UserInfo[2] = Password

    public static void createUserInfo(String userType) throws Exception {

        try (PrintWriter out = new PrintWriter(userType + "UserInfo.txt")) {
            switch (userType) {
                case "depEd":
                    for (String[] userInfo1 : depEpUserInfo) {
                        out.println(Arrays.toString(userInfo1).replace('[', ' ').replace(']', ' ').trim());
                    }
                    break;
                case "doJ":
                    for (String[] userInfo1 : doJUserInfo) {
                        out.println(Arrays.toString(userInfo1).replace('[', ' ').replace(']', ' ').trim());
                    }
                    break;
                case "pnp":
                    for (String[] userInfo1 : pnpUserInfo) {
                        out.println(Arrays.toString(userInfo1).replace('[', ' ').replace(']', ' ').trim());
                    }
                    break;
            }
        }
    }

    private static void readUserInfo(String userType) throws Exception {

        String[][] userInfo1 = new String[USER_DETAILS][USER_CREATION_LIMIT];

        File file = new File(userType + "UserInfo.txt");
        try (Scanner in = new Scanner(file)) {
            while (in.hasNextLine()) {
                for (String[] userInfo11 : userInfo1) {
                    String[] line = in.nextLine().trim().split("," + " ");
                    for (int j = 0; j < line.length; j++) {
                        userInfo11[j] = String.valueOf(line[j]);
                    }
                }
            }
        }

        switch (userType) {
            case "depEd":
                depEpUserInfo = userInfo1;
                break;
            case "doJ":
                doJUserInfo = userInfo1;
                break;
            case "pnp":
                pnpUserInfo = userInfo1;
                break;
            default:
                break;
        }
    }

    private static void setNullValues(String[][] userType) {

        for (String[] userInfo : userType) {
            for (int j = 0; j < userInfo.length; j++) {
                if (userInfo[j].equals("null")) {
                    userInfo[j] = null;
                }
            }
            break;
        }
    }

    public static void main(String[] args) throws Exception {

        if (new File("depEdUserInfo.txt").exists()) {
            readUserInfo("depEd");
            setNullValues(depEpUserInfo);
            createUserInfo("depEd");
        }

        if (new File("doJUserInfo.txt").exists()) {
            readUserInfo("doJ");
            setNullValues(doJUserInfo);
            createUserInfo("doJ");
        }

        if (new File("pnpUserInfo.txt").exists()) {
            readUserInfo("pnp");
            setNullValues(pnpUserInfo);
            createUserInfo("pnp");
        }

        new Login().setVisible(true);
    }
}
