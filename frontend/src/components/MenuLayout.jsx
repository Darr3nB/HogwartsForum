import {useEffect, useState} from "react";
import {Link} from "react-router-dom";
import {utility} from "../utility.js";


async function doLogin(e, setLoginState) {
    e.preventDefault();
    const componentData = new FormData(e.currentTarget);


    await utility.apiPostWithDictionaryDataType(`/user/login`, {
        'username': componentData.get('username-field'),
        'password': componentData.get('password-field')
    })
        .then(response => {
            if (response.ok) {
                setLoginState(true);
            }
        });
}

async function doLogout(e, setLoginState) {
    e.preventDefault();

    await utility.apiGet(`/user/logout`)
        .then(response => {
            if (response.ok) {
                setLoginState(false);
            }
        })
}

export default function MenuLayout() {
    // TODO switch password input field type to password
    const [isLoggedIn, setLoginState] = useState(false);
    const [loggedInUser, setLoggedInUser] = useState({});

    const isLoggedInRequest = async () => {
        return await utility.apiGet(`/user/logged-in`)
            .then(r => {
                if (r.status === 204) {
                    return false;
                } else if (r.status === 200) {
                    return r.json();
                }
            });
    };

    useEffect(() => {
        isLoggedInRequest().then(
            d => {
                if (d === false) {
                    setLoginState(false);
                } else {
                    setLoggedInUser(d);
                    setLoginState(true);
                }
            }
        );
    }, [isLoggedIn]);


    const loggedOff = <div className="wrapper">
        <Link to={"/"} className="menu-layout-elements">
            <button type="button">Home</button>
        </Link>
        <div>
            <Link to={"/registration"} className="menu-layout-elements">
                <button type="button">Registration</button>
            </Link>
        </div>
        <div className="first, go-right">
            <form onSubmit={event => doLogin(event, setLoginState)}>
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" name="username-field" minLength="3"/>
                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" name="password-field" minLength="3"/>

                <button type="submit" id="login-button" className="nav-bar-button">Login</button>
            </form>
        </div>

    </div>;

    const loggedIn = <div className="wrapper">
        <Link to={"/"} className="menu-layout-elements">
            <button type="button">Home</button>
        </Link>
        <p className="menu-layout-elements">Welcome {loggedInUser.name}!</p>
        <button onClick={event => doLogout(event, setLoginState)} className="menu-layout-buttons, go-right">Logout</button>
        <Link to={"/profile"} className="menu-layout-buttons, go-right">
            <button type="button" className="profile-button">Profile</button>
        </Link>
    </div>;

    return isLoggedIn === false ? loggedOff : loggedIn;
};
