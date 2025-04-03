import React, { useEffect } from "react";
import { useAuth0 } from "@auth0/auth0-react";
import { useNavigate } from "react-router-dom";

const Callback = () => {
  const { isLoading, error, user, getAccessTokenSilently } = useAuth0();
  const navigate = useNavigate();

  useEffect(() => {
    const handleAuth = async () => {
      if (!isLoading && !error && user) {
        try {
          const token = await getAccessTokenSilently();
          localStorage.setItem("token", token);
          localStorage.setItem("idp", "auth0");
          navigate("/");
        } catch (err) {
          console.error("Error getting token:", err);
        }
      }
    };

    handleAuth();
  }, [isLoading, error, user, getAccessTokenSilently]);

  return (
    <div>
      <h2>Loading...</h2>
    </div>
  );
};

export default Callback;
