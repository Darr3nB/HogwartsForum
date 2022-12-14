import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {Link} from "react-router-dom";

export default function Registration() {
    return (
        <div>
            <MenuLayout/>

            <h1>Registration</h1>

            <form action="/registration" method="post">
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" minLength="5"/>
                <p id="valid-field"></p>

                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" minLength="3"/>

                <label htmlFor="password-again-field">Magic word again: </label>
                <input type="text" id="password-again-field" minLength="3"/>

                <label htmlFor="house-field">Your house: </label>
                <select id="house-field">
                    <option value="noneSelected"></option>
                    <option value="Gryffindor">Gryffindor</option>
                    <option value="Slytherin">Slytherin</option>
                    <option value="Hufflepuff">Hufflepuff</option>
                    <option value="Ravenclaw">Ravenclaw</option>
                </select>

                <label htmlFor="pet-field">Your pet: </label>
                <select id="pet-field">
                    <option value="noneSelected"></option>
                    <option value="Owl">Owl</option>
                    <option value="Car">Cat</option>
                    <option value="Toad">Toad</option>
                    <option value="Rat">Rat</option>
                    <option value="Ferret">Ferret</option>
                </select>

                <Link to={"/registration"}>
                    <button type="button" id="registration-button">Registration</button>
                </Link>
            </form>

            <Footer/>
        </div>
    );
}
