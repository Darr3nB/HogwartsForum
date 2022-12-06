import './App.css';
import {useState} from "react";
import MenuLayout from "./components/MenuLayout";
import Footer from "./components/Footer";

function App() {
    return (
        <div className="App">
            <MenuLayout/>
            <Footer/>
        </div>
    );
}

export default App;
