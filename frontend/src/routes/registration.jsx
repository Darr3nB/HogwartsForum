import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {Link} from "react-router-dom";

export default function Registration() {
    return (
        <div>
            <MenuLayout/>

            <h1 className="header-to-middle">Registration</h1>

            <div className="card-to-middle-with-border">
                <form action="/registration" method="post">
                    <label htmlFor="username-field" className="reg-fields">Username: </label>
                    <input type="text" id="username-field" minLength="5" className="reg-fields"/>
                    <p id="valid-field"></p>

                    <label htmlFor="password-field" className="reg-fields">Magic word: </label>
                    <input type="text" id="password-field" minLength="3" className="reg-fields"/>

                    <label htmlFor="password-again-field" className="reg-fields">Magic word again: </label>
                    <input type="text" id="password-again-field" minLength="3" className="reg-fields"/>

                    <label htmlFor="house-field" className="reg-fields">Your house: </label>
                    <select id="house-field" className="reg-fields">
                        <option value="noneSelected"></option>
                        <option value="Gryffindor">Gryffindor</option>
                        <option value="Slytherin">Slytherin</option>
                        <option value="Hufflepuff">Hufflepuff</option>
                        <option value="Ravenclaw">Ravenclaw</option>
                    </select>

                    <label htmlFor="pet-field" className="reg-fields">Your pet: </label>
                    <select id="pet-field" className="reg-fields">
                        <option value="noneSelected"></option>
                        <option value="Owl">Owl</option>
                        <option value="Car">Cat</option>
                        <option value="Toad">Toad</option>
                        <option value="Rat">Rat</option>
                        <option value="Ferret">Ferret</option>
                    </select>

                    <Link to={"/registration"} className="button-to-middle">
                        <button type="button" id="registration-button">Registration</button>
                    </Link>
                </form>
            </div>

            <Footer/>
        </div>
    );
}
