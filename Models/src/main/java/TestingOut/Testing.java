package TestingOut;

public class Testing {
    public static void main(String[] args) {
        UserInfo userInfo = new UserInfo("Norsi");
        userInfo.setAge(45);
        userInfo.setFirstName("Norsi");
        userInfo.setLastName("Onye");

        System.out.println(userInfo + String.format("RandomName %s", userInfo));
    }



}
