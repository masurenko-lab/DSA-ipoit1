package by.it.group410972.masurenko.lesson04;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Scanner;

/*
Реализуйте сортировку слиянием для одномерного массива.
Сложность алгоритма должна быть не хуже, чем O(n log n)

Первая строка содержит число 1<=n<=10000,
вторая - массив A[1…n], содержащий натуральные числа, не превосходящие 10E9.
Необходимо отсортировать полученный массив.

Sample Input:
5
2 3 9 2 9
Sample Output:
2 2 3 9 9
*/
public class B_MergeSort {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream stream = B_MergeSort.class.getResourceAsStream("dataB.txt");
        B_MergeSort instance = new B_MergeSort();
        //long startTime = System.currentTimeMillis();
        int[] result = instance.getMergeSort(stream);
        //long finishTime = System.currentTimeMillis();
        for (int index : result) {
            System.out.print(index + " ");
        }
    }

    int[] getMergeSort(InputStream stream) throws FileNotFoundException {
        //подготовка к чтению данных
        Scanner scanner = new Scanner(stream);
        //!!!!!!!!!!!!!!!!!!!!!!!!!     НАЧАЛО ЗАДАЧИ     !!!!!!!!!!!!!!!!!!!!!!!!!

        //размер массива
        int n = scanner.nextInt();
        //сам массив
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = scanner.nextInt();
            System.out.println(a[i]);
        }

        // Запускаем сортировку слиянием
        mergeSort(a, 0, n - 1);

        return a;
    }

    // Рекурсивная функция сортировки слиянием
    void mergeSort(int[] a, int left, int right) {
        if (left < right) {
            // Находим середину текущего подмассива
            int mid = (left + right) / 2;

            // Рекурсивно сортируем левую половину
            mergeSort(a, left, mid);

            // Рекурсивно сортируем правую половину
            mergeSort(a, mid + 1, right);

            // Объединяем отсортированные половины
            merge(a, left, mid, right);
        }
    }

    // Функция для объединения двух отсортированных подмассивов
    void merge(int[] a, int left, int mid, int right) {
        // Вычисляем размеры временных подмассивов
        int n1 = mid - left + 1;
        int n2 = right - mid;

        // Создаем временные массивы
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Копируем данные во временные массивы
        for (int i = 0; i < n1; ++i)
            L[i] = a[left + i];
        for (int j = 0; j < n2; ++j)
            R[j] = a[mid + 1 + j];

        // Индексы для временных массивов и основного массива
        int i = 0, j = 0, k = left;

        // Сливаем временные массивы обратно в основной
        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                a[k] = L[i];
                i++;
            } else {
                a[k] = R[j];
                j++;
            }
            k++;
        }

        // Копируем оставшиеся элементы, если остались
        while (i < n1) {
            a[k] = L[i];
            i++;
            k++;
        }
        while (j < n2) {
            a[k] = R[j];
            j++;
            k++;
        }
    }
}