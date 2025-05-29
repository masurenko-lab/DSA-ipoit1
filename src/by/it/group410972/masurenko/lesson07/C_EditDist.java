package by.it.group410972.masurenko.lesson07;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Задача на программирование: расстояние Левенштейна
    https://ru.wikipedia.org/wiki/Расстояние_Левенштейна
    http://planetcalc.ru/1721/

Дано:
    Две данных непустые строки длины не более 100, содержащие строчные буквы латинского алфавита.

Необходимо:
    Решить задачу МЕТОДАМИ ДИНАМИЧЕСКОГО ПРОГРАММИРОВАНИЯ
    Итерационно вычислить алгоритм преобразования двух данных непустых строк
    Вывести через запятую редакционное предписание в формате:
     операция("+" вставка, "-" удаление, "~" замена, "#" копирование)
     символ замены или вставки

    Sample Input 1:
    ab
    ab
    Sample Output 1:
    #,#,

    Sample Input 2:
    short
    ports
    Sample Output 2:
    -s,~p,#,#,#,+s,

    Sample Input 3:
    distance
    editing
    Sample Output 2:
    +e,#,#,-s,#,~i,#,-c,~g,


    P.S. В литературе обычно действия редакционных предписаний обозначаются так:
    - D (англ. delete) — удалить,
    + I (англ. insert) — вставить,
    ~ R (replace) — заменить,
    # M (match) — совпадение.
*/


public class C_EditDist {

    String getDistanceEdinting(String one, String two) {
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
     //   String result = "";
        int n = one.length();
        int m = two.length();
        int[][] mass = new int[n + 1][m + 1];
        String[][] result = new String[n + 1][m + 1];

        // Инициализация
        for (int i = 0; i <= n; i++) {
            mass[i][0] = i;
            result[i][0] = i == 0 ? "" : result[i - 1][0] + "-"+one.charAt(i - 1)+",";
        }

        for (int j = 0; j <= m; j++) {
            mass[0][j] = j;
            result[0][j] = j == 0 ? "" : result[0][j - 1] + "+"+two.charAt(j - 1)+",";
        }

        // Основной цикл
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                char a = one.charAt(i - 1);
                char b = two.charAt(j - 1);
                int costReplace = mass[i - 1][j - 1] + (a == b ? 0 : 1);
                int costInsert = mass[i][j - 1] + 1;
                int costDelete = mass[i - 1][j] + 1;
                mass[i][j] = Math.min(costReplace, Math.min(costInsert, costDelete));
                if (mass[i][j] == costReplace) {
                    result[i][j] = result[i - 1][j - 1] + (a == b ? "#," : "~" + b + ",");
                }
                else if (mass[i][j] == costInsert) {
                    result[i][j] = result[i][j - 1] + "+" + b + ",";
                }
                else {
                    result[i][j] = result[i - 1][j] + "-" + a + ",";
                }
            }
        }
        //!!!!!!!!!!!!!!!!!!!!!!!!!     КОНЕЦ ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!
        return result[n][m];
    }


    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = C_EditDist.class.getResourceAsStream("dataABC.txt");
        C_EditDist instance = new C_EditDist();
        Scanner scanner = new Scanner(stream);
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
        System.out.println(instance.getDistanceEdinting(scanner.nextLine(),scanner.nextLine()));
    }

}