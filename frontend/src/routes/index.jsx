import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {Outlet} from "react-router-dom";

export default function Index() {
    return (
        <div className="App">
            <MenuLayout/>
            <Outlet/>
            <Footer/>
        </div>
    );
}
