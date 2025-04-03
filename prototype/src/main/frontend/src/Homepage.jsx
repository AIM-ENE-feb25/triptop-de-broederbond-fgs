import React, { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";
import LogoutButton from "./Logout";

import { accessProtected } from "./api";

const HomePage = () => {
  const navigate = useNavigate();
  const [token, setToken] = useState(null);
  const [idp, setIdp] = useState(null);

  useEffect(() => {
    const tokenFromStorage = localStorage.getItem("token");
    const idpFromStorage = localStorage.getItem("idp");
    setToken(tokenFromStorage);
    setIdp(idpFromStorage);
  }, []);

  return (
    <div>
      <nav>
        {!token && <button onClick={() => navigate("/login")}>Login</button>}
        {token && <LogoutButton />}
      </nav>
      <h1>Prototype</h1>
      {token ? (
        <p>Authenticated with token: {token}</p>
      ) : (
        <p>You are not logged in.</p>
      )}
      <button onClick={() => accessProtected(token, idp)}>
        Access protected data
      </button>
    </div>
  );
};

export default HomePage;
