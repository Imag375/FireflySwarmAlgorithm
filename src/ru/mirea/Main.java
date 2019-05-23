package ru.mirea;

public class Main {

    final static double ATRACTIVENESS = 0.7;             //коэффициент привлекательности светлячка
    final static double NUMBER_OF_NEIGHBORS = 0.7;       //параметр для управления количеством соседних агентов
    final static double BETA = 0.7;                      //параметр для определения радиуса окрестности
    final static double LUCIFERIN_DISINTEGRATION = 0.3;  //коэффициент распада люциферина
    final static double NEW_POSITION_GENERATOR = 0.5;     //генератор новой позиции
    final static int MAX_NUMBER_OF_ITERATION = 20000;      //максимальное число итераций
    final static int SIZE_SWARM = 30;                   //размер популяции
    final static double MIN_XY = -5;                    //минимальное значение вектора позиции
    final static double MAX_XY = 5;                    //масимальное значение вектора позиции
    final static double MAX_R = 1;                     //максимальный радиус окрестности
    final static double MIN_R = 0.3;                     //минимальный радиус окрестности

    public static void main(String[] args) {
        FireflySwarmAlgorithm fireflySwarmAlgorithm = new FireflySwarmAlgorithm(MAX_NUMBER_OF_ITERATION, SIZE_SWARM, MIN_XY, MAX_XY, 0.5, MAX_R, ATRACTIVENESS, NUMBER_OF_NEIGHBORS, BETA, LUCIFERIN_DISINTEGRATION, NEW_POSITION_GENERATOR, MAX_R, MIN_R);
        System.out.println("Ответ: " + fireflySwarmAlgorithm.runFireflySwarmAlgorithm());
    }
}
