import {useState} from "react";
import {Link} from "react-router-dom";
import {utility} from "../utility.js";


async function doLogin(e, setLoginState) {
    e.preventDefault();
    const componentData = new FormData(e.currentTarget);


    await utility.apiPostWithDictionaryDataType(`/login`, {
        'username': componentData.get('username-field'),
        'password': componentData.get("password-field")
    })
        .then(response => {
            if (response.ok) {
                setLoginState(true);
            }
        });
}

async function doLogout(e, setLoginState) {
    e.preventDefault();

    await utility.apiGet(`/logout`)
        .then(response => {
            if (response.ok) {
                setLoginState(false);
            }
        })
}

export default function MenuLayout() {
    // TODO request from backend if logged in
    // TODO request logged in user's data for usage
    // TODO switch password input field type to password
    const [isLoggedIn, setLoginState] = useState(false);

    const loggedOff = <div>
        <Link to={"/"}>
            <button type="button">Home</button>
        </Link>
        <div>
            <form onSubmit={event => doLogin(event, setLoginState)}>
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" name="username-field" minLength="3"/>
                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" name="password-field" minLength="3"/>

                <button type="submit" id="login-button" className="nav-bar-button">Login</button>
            </form>
        </div>
        <div>
            <Link to={"/registration"}>
                <button type="button">Registration</button>
            </Link>
        </div>
    </div>;

    const loggedIn = <div>
        <Link to={"/"}>
            <button type="button">Home</button>
        </Link>
        <p>Welcome USERNAME_HERE!</p>
        <button onClick={event => doLogout(event, setLoginState)}>Logout</button>
        <Link to={"/profile"}>
            <button type="button">Profile</button>
        </Link>
    </div>;

    return isLoggedIn ? loggedIn : loggedOff;
};
