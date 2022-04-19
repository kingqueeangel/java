public class test {
    public static void main(String[] args) {


        String str = "  username=aaa660;  password=lina860402";
        String name = "username" + "=";
        String res = null;
        String[] ca = str.split(";");
        System.out.println(ca.length);
        for (int i = 0; i < ca.length; i++) {
            String c = ca[i];
            System.out.println("外部第"+i+"次的C："+c);
            while (c.charAt(0) == ' ') {
                c = c.substring(1);
                System.out.println("第"+i+"次的C："+c);
            }
            if (c.indexOf(name) == 0) {
                res = c.substring(name.length(), c.length());

            }
        }
        System.out.println("最后结果为："+res);


    }
}

