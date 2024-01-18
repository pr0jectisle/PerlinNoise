class Pheromone {
    int x;
    int y;
    float strength;
    float decaySpeed;

    int[] rgbA;
    int[] rgbP;
    int size;

    Pheromone(int x, int y, float pheroDecay, int[] rgbA, int[] rgbP, int size) {
        this.x = x;
        this.y = y;
        this.decaySpeed = pheroDecay;

        this.rgbA = rgbA;
        this.rgbP = rgbP;
        this.size = size;
        this.strength = 1;

    }

    void decay() {
        this.strength += -decaySpeed;
    }

    void show() {

        color colourA = color(rgbA[0], rgbA[1], rgbA[2]);
        color colourP = color(rgbP[0], rgbP[1], rgbP[2]);
        color trailColor = lerpColor(colourA, colourP, 1 - strength);

        fill(trailColor);
        rect(x, y, size, size);
    }
}