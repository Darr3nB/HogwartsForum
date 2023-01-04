import {useEffect, useState} from "react";
import {Link, useNavigate} from "react-router-dom";
import {utility} from "../utility.js";


async function doLogin(e, setLoginState, navigate) {
    e.preventDefault();
    const componentData = new FormData(e.currentTarget);


    await utility.apiPostWithDictionaryDataType(`/user/login`, {
        'username': componentData.get('username-field'),
        'password': componentData.get('password-field')
    })
        .then(response => {
            if (response.ok) {
                setLoginState(true);
                navigate("/");
            }
        });
}

async function doLogout(e, setLoginState, navigate) {
    e.preventDefault();

    await utility.apiGet(`/user/logout`)
        .then(response => {
            if (response.ok) {
                setLoginState(false);
                navigate("/");
            }
        })
}

export default function MenuLayout() {
    // TODO switch password input field type to password
    const [isLoggedIn, setLoginState] = useState(false);
    const [loggedInUser, setLoggedInUser] = useState({});
    const navigate = useNavigate();

    useEffect(() => {
        utility.isLoggedInRequest().then(
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
        <span className="hovertext" data-hover="Home page">
            <Link to={"/"} className="menu-layout-elements">
                <button type="button" className="profile-button" id="home-button"></button>
            </Link>
        </span>
        <div>
            <Link to={"/registration"} className="menu-layout-elements">
                <button type="button">Registration</button>
            </Link>
        </div>
        <div className="first, go-right">
            <form onSubmit={event => doLogin(event, setLoginState, navigate)}>
                <label htmlFor="username-field">Username: </label>
                <input type="text" id="username-field" name="username-field" minLength="3"/>
                <label htmlFor="password-field">Magic word: </label>
                <input type="text" id="password-field" name="password-field" minLength="3"/>

                <button type="submit" id="login-button" className="nav-bar-button">Login</button>
            </form>
        </div>

    </div>;

    const houseCrest = () => {
        switch (loggedInUser.house){
            case "GRYFFINDOR":
                return "gryffindor-house-crest";
            case "SLYTHERIN":
                return "slytherin-house-crest";
            case "HUFFLEPUFF":
                return "hufflepuff-house-crest";
            case "RAVENCLAW":
                return "ravenclaw-house-crest";
            default:
                return "profile-button";
        }
    }

    const loggedIn = <div className="wrapper">
        <span className="hovertext" data-hover="Home page">
            <Link to={"/"} className="menu-layout-elements">
                <button type="button" className="profile-button" id="home-button"></button>
            </Link>
        </span>
        <p className="menu-layout-elements slight-white-background">Welcome {loggedInUser.name}!</p>
        <button onClick={event => doLogout(event, setLoginState, navigate)} className="menu-layout-buttons, go-right">Logout
        </button>
        <span className="hovertext" data-hover="Profile page">
            <Link to={"/profile"} className="menu-layout-buttons, go-right">
                <button id="profile-button" type="button" className={houseCrest()} title="Profile"></button>
            </Link>
        </span>
    </div>;

    return isLoggedIn === false ? loggedOff : loggedIn;
};
