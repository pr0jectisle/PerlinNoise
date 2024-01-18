class Agent {
    float x;
    float y;
    float angle;
    float shift;
    float speed;
    String noise;

    float noiseScale;
    int size;
    int[] rgb;
    boolean toDel;

    Agent(float speed, float shift, String noise, float noiseScale, int size, int[] rgb) {
        this.speed = speed;
        this.shift = shift;
        this.noise = noise;
        this.noiseScale = noiseScale;
        this.size = size;
        this.rgb = rgb;
        this.x = random(width);
        this.y = random(height);
        this.toDel = false;
    }

    void update(float shif) {
        this.angle = (2 * PI * noise(this.x * noiseScale, this.y * noiseScale)) + shif;
        Vector dir = new Vector(cos(angle), sin(angle));
        this.x = this.x + speed * dir.x;
        this.y = this.y + speed * dir.y;
        // println("x : " + x + ", y : " + y);
    }

    void show() {
        fill(rgb[0], rgb[1], rgb[2]);
        noStroke();
        rect(this.x, this.y, this.size, this.size);
        if (x <= 0 || x >= width || y <= 0 || y >= height) {
            toDel = true;
        }
    }
}
