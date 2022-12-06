const MenuLayout = () => {
  return (
      <div>
        <button>Home</button>
        <div>
          <label for="username-field">Username: </label>
          <input type="text" id="username-field" minLength="3"/>
          <label for="password-field">Magic word: </label>
          <input type="text" id="password-field" minLength="3"/>
          <button type="button" id="login-button" class="nav-bar-button">Login</button>
        </div>
        <div>
          <button type="button">Registration</button>
        </div>
      </div>
  );
}

export default MenuLayout;