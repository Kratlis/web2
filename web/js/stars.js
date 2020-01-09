window.requestAnimationFrame = window.requestAnimationFrame || window.webkitRequestAnimationFrame || window.mozRequestAnimationFrame;

const canvas = document.getElementById("stars");
// canvas.height = 100;
// canvas.height = 100;
const ctx = canvas.getContext("2d");
console.log(canvas.height+'   '+canvas.width);
// let w = canvas.width;
let w = canvas.width = window.innerWidth;
let h = canvas.height = window.innerHeight;
// let h = window.innerHeight;
console.log(w+", "+h);
console.log(window.pageXOffset);
var particles = {};
var particleIndex = 0;
var maxParticles = 2;
var hue = 0;
var mouseX, mouseY;

var img = new Image();
img.src = 'star.png';

function resizeCanvas(){
    w = canvas.width = window.innerWidth;
    h = canvas.height = window.innerHeight;
}


function Particle(){
    this.x = mouseX;
    this.y = mouseY;
    if (mouseX == null || mouseY == null){
        return;
    }
    this.size = this.random(30);
    this.maxLife = this.random(100);
    this.life = 0;
    this.vx = this.random(-1, 1);
    this.vy = this.random(-1, 1);
    this.grav = .003;
    this.index = particleIndex;
    particles[particleIndex] = this;
    particleIndex++;
}

Particle.prototype = {

    constructor: Particle,

    draw: function(){
        ctx.drawImage(img, this.x, this.y, this.size, this.size);
        this.update();
    },

    update: function(){
        if(this.life >= this.maxLife){
            delete particles[this.index];
        }
        this.x += this.vx;
        this.y += this.vy;
        this.vy += this.grav;
        this.life++;
    },

    random: function(){
        const min = arguments.length === 1 ? 0 : arguments[0],
            max = arguments.length === 1 ? arguments[0] : arguments[1];
        return Math.random() * (max - min) + min;
    }

};

function animate(){
    let i;
    ctx.fillStyle = "rgba(255,255,255,1)";
    ctx.fillRect(0,0,w,h);


    for(i = 0; i < maxParticles; i++){
        particles[i] = new Particle();
    }
    for(i in particles){
        particles[i].draw();
    }

    hue += .3;
    window.requestAnimationFrame(animate);
}


window.addEventListener("resize", function(){
    resizeCanvas();
});

window.addEventListener("mousemove", function(e){
    mouseX=e.clientX;
    mouseY=e.clientY;
    console.log( mouseX +", top: "+ mouseY)
});

canvas.addEventListener("click", function (e) {
    ctx.fillStyle='#346455';
    ctx.fillRect(mouseX, mouseY, 10, 10);
    console.log(mouseX+'   '+mouseY);
    ctx.fillStyle = "#000000";
    ctx.fillRect(0,0,50,30);
});

canvas.addEventListener("mouseleave", function(e){
    mouseX = null;
    mouseY = null;
});

animate();