import React, { useState } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import { useNavigate } from "react-router-dom";

import { mockApiLogin } from "./api";

const Login = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const [error, setError] = useState("");
  const { loginWithRedirect } = useAuth0();
  const navigate = useNavigate();

  const handleCustomLogin = async () => {
    try {
      const token = await mockApiLogin(username, password);
      localStorage.setItem("token", token);
      localStorage.setItem("idp", "mockapi");

      navigate("/");
    } catch (err) {
      setError("Invalid credentials. Please try again.");
    }
  };

  return (
    <div style={{ textAlign: "center", marginTop: "50px" }}>
      <h2>Login</h2>
      <div>
        <button onClick={() => loginWithRedirect()}>Login with Auth0</button>
      </div>
      <hr />
      <div>
        <h3>Or</h3>
        <input
          type="text"
          placeholder="Username"
          value={username}
          onChange={(e) => setUsername(e.target.value)}
        />
        <br />
        <input
          type="password"
          placeholder="Password"
          value={password}
          onChange={(e) => setPassword(e.target.value)}
        />
        <br />
        <button onClick={handleCustomLogin}>
          Login with Custom Credentials
        </button>
      </div>
      {error && <p style={{ color: "red" }}>{error}</p>}
    </div>
  );
};

export default Login;
