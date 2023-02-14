import MenuLayout from "../components/MenuLayout.jsx";
import Footer from "../components/Footer.jsx";
import {useEffect, useState} from "react";
import {utility} from "../utility.js";
import {useNavigate} from "react-router-dom";

export default function Registration() {
    const navigate = useNavigate();
    const [isLoggedIn, setLoginState] = useState(false);

    useEffect(() => {
        utility.loggedInUser().then(
            d => {
                if (d === false) {
                    setLoginState(false);
                } else {
                    setLoginState(true);
                    navigate("/");
                }
            }
        );
    }, [isLoggedIn]);

    const doRegistration = async (event) => {
        event.preventDefault();
        const componentData = new FormData(event.currentTarget);

        await utility.apiPostWithDictionaryDataType("/user/registration", {
            "username": componentData.get("username-field"),
            "password": componentData.get("password-field"),
            "passwordAgain": componentData.get("password-again-field"),
            "house": componentData.get("house-field"), "petType": componentData.get("pet-field")
        })
            .then(response => {
                if (response.ok) {
                    navigate("/");
                }
            });
    }

    return (
        <div>
            <MenuLayout/>

            <h1 className="header-to-middle">Registration</h1>

            <div className="card-to-middle-with-border">
                <form onSubmit={event => doRegistration(event)}>
                    <label htmlFor="username-field" className="reg-fields">Username: </label>
                    <input type="text" id="username-field" name="username-field" minLength="5" className="reg-fields"/>
                    <p id="valid-field"></p>

                    <label htmlFor="password-field" className="reg-fields">Magic word: </label>
                    <input type="text" id="password-field" name="password-field" minLength="3" className="reg-fields"/>

                    <label htmlFor="password-again-field" className="reg-fields">Magic word again: </label>
                    <input type="text" id="password-again-field" name="password-again-field" minLength="3"
                           className="reg-fields"/>

                    <label htmlFor="house-field" className="reg-fields">Your house: </label>
                    <select id="house-field" name="house-field" className="reg-fields">
                        <option value="noneSelected"></option>
                        <option value="Gryffindor">Gryffindor</option>
                        <option value="Slytherin">Slytherin</option>
                        <option value="Hufflepuff">Hufflepuff</option>
                        <option value="Ravenclaw">Ravenclaw</option>
                    </select>

                    <label htmlFor="pet-field" className="reg-fields">Your pet: </label>
                    <select id="pet-field" name="pet-field" className="reg-fields">
                        <option value="noneSelected"></option>
                        <option value="Owl">Owl</option>
                        <option value="Car">Cat</option>
                        <option value="Toad">Toad</option>
                        <option value="Rat">Rat</option>
                        <option value="Ferret">Ferret</option>
                    </select>

                    <button type="submit" id="registration-button" className="reg-fields">Registration</button>
                </form>
            </div>

            <Footer/>
        </div>
    );
}
