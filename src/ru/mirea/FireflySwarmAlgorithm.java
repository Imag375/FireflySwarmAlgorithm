package ru.mirea;

public class FireflySwarmAlgorithm {
    final double attractiveness;                 //коэффициент привлекательности светлячка
    final double numOfNeighbors;                //параметр для управления количеством соседних агентов
    final double beta;              //параметр для определения радиуса окрестности
    final double luciferinDisintegration;                 //коэффициент распада люциферина
    final double newPositionGenerator;   //генератор новой позиции
    final int maxNumOfIteration;        //максимальное число итераций
    final int sizeSwarm;                //размер популяции
    final double minXY;                 //минимальное значение вектора позиции
    final double maxXY;                 //масимальное значение вектора позиции
    final double maxR;                  //максимальный радиус окрестности
    final double minR;                  //минимальный радиус окрестности

    private Firefly[] firefly;

    public FireflySwarmAlgorithm(int maxNumOfIteration, int sizeSwarm, double minXY, double maxXY, double l0, double r0, double attractiveness, double numOfNeighbors, double beta, double luciferinDisintegration, double newPositionGenerator, double maxR, double minR) {
        this.maxNumOfIteration = maxNumOfIteration;
        this.sizeSwarm = sizeSwarm;
        this.minXY = minXY;
        this.maxXY = maxXY;
        this.attractiveness = attractiveness;
        this.numOfNeighbors = numOfNeighbors;
        this.beta = beta;
        this.luciferinDisintegration = luciferinDisintegration;
        this.newPositionGenerator = newPositionGenerator;
        this.maxR = maxR;
        this.minR = minR;

        firefly = new Firefly[sizeSwarm];
        for(int i = 0; i < sizeSwarm; i++) {
            firefly[i] = new Firefly(minXY, maxXY);
            firefly[i].setL(l0);
            firefly[i].setR(r0);
        }

    }

    public double runFireflySwarmAlgorithm() {
        double probability;
        double randFirefly;
        double bestFirefly = 0;
        int flag;
        for(int k = 0; k < maxNumOfIteration; k++) {
            for (Firefly f : firefly) {  //обновление количества люциферина
                newL(f);
            }

            for (int i = 0; i < sizeSwarm; i++) {    //определение направления дальнейшего движения
                probability = 0;
                randFirefly = Math.random();
                flag = 0;
                for (int j = 0; j < sizeSwarm; j++) {
                    if (theySeeEachOther(firefly[i], firefly[j])) {
                        probability += getProbability(firefly[i], firefly[j]);
                        if (randFirefly <= probability) {
                            newXY(firefly[i], firefly[j]);
                            flag = 1;
                            break;
                        }
                    }
                }
                if(flag != 1) {
                    firefly[i].setX(firefly[i].getX() + Math.random() * 2 - 1);
                    firefly[i].setY(firefly[i].getY() + Math.random() * 2 - 1);
                }
            }
            for (Firefly f : firefly) {  //обновление радиуса окрестности
                newR(f);
                if(f.getF() < bestFirefly) {
                    bestFirefly = f.getF();
                }
            }
        }
        return bestFirefly;
    }

    private void newL(Firefly f) {
        f.setL((1 - luciferinDisintegration) * f.getL() - attractiveness * f.getF());
    }

    private boolean theySeeEachOther(Firefly fi, Firefly fj) {
        if(Math.pow(fj.getX()+fi.getX(), 2) + Math.pow(fj.getY()+fi.getY(), 2) <= Math.pow(fi.getR(), 2)) {
            return true;
        }
        else {
            return false;
        }
    }

    private double getProbability(Firefly f1, Firefly f2) {
        double sum = 0;
        for(Firefly f : firefly) {
            if(theySeeEachOther(f1, f)) {
                sum += f.getL() - f1.getL();
            }
        }
        return (f2.getL() - f1.getL()) / sum;
    }

    private void newXY(Firefly f1, Firefly f2) {
        f1.setX(f1.getX() + newPositionGenerator * (f2.getX() - f1.getX()) / Math.pow(Math.pow(f2.getX() - f1.getX(), 2) + Math.pow(f2.getY() - f1.getY(), 2), 0.5));
        f1.setY(f1.getY() + newPositionGenerator * (f2.getY() - f1.getY()) / Math.pow(Math.pow(f2.getX() - f1.getX(), 2) + Math.pow(f2.getY() - f1.getY(), 2), 0.5));
    }

    private void newR(Firefly f) {
        double max;
        if(minR > (f.getR() + beta * (numOfNeighbors - Math.pow(f.getR(), 2) * Math.PI))) {
            max = minR;
        }
        else {
            max = f.getR() + beta * (numOfNeighbors - Math.pow(f.getR(), 2) * Math.PI);
        }
        if(maxR < max) {
            f.setR(maxR);
        }
        else {
            f.setR(max);
        }
    }
}
