import { useAuth0 } from "@auth0/auth0-react";
import React, { useEffect, useState } from "react";

const LogoutButton = () => {
  const { logout } = useAuth0();

  const [idp, setIdp] = useState(null);

  useEffect(() => {
    const idpFromStorage = localStorage.getItem("idp");
    setIdp(idpFromStorage);
  }, []);

  return (
    <button
      onClick={() => {
        localStorage.removeItem("token");
        localStorage.removeItem("idp");

        if (idp === "auth0") {
          logout({ logoutParams: { returnTo: window.location.origin } });
        } else {
          window.location.reload();
        }
      }}
    >
      Log Out
    </button>
  );
};

export default LogoutButton;
