class Pheromone{
    int x;
    int y;
    float strength;
    float decaySpeed;
  
    color rgbA;
    color rgbP;
    int size;
    
    Pheromone(int x, int y, float pheroDecay,color rgbA, color rgbP ,int size){
      this.x = x;
      this.y = y;
      this.decaySpeed = pheroDecay;
      this.rgbA = rgbA;
      this.rgbP = rgbP;
      this.size = size;
      this.strength = 1;
      
    }
    void decay(){
      this.strength += - decaySpeed;
    }
    void show(){
      color trailColor = lerpColor(rgbA, rgbP, 1-strength);
      fill(trailColor);
      ellipse(x,y,size,size);
    }
  }