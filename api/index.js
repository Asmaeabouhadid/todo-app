const express =require("express");
const bodyParser =require("body-parser");
const mongoose =require("mongoose");
const crypto =("crypto");

const app =express();
const port=3000;
const cors =require("cors");
app.use(cors());

app.use(bodyParser.urlencoded({extended:false}));
app.use(bodyParser.json());

mongoose.connect("mongodb+srv://asmae:asmae@cluster0.jizhm6i.mongodb.net/").then(() => {
    console.log("Connected to MongoDB");
}).catch((error) => {
    console.log("Error connecting to MongoDB:", error.message);
});



app.listen(port, ()=>{
    console.log("server is runing on port 3000")
});