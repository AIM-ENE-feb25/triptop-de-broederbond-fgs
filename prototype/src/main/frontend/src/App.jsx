import React from "react";
import { createBrowserRouter, RouterProvider } from "react-router";

import { Auth0Provider } from "@auth0/auth0-react";
import authConfig from "./auth_config.json";
import Login from "./Login";
import Callback from "./Callback";
import HomePage from "./HomePage";

const router = createBrowserRouter([
  {
    path: "/",
    element: <HomePage />,
  },
  {
    path: "/login",
    element: <Login />,
  },
  {
    path: "/callback",
    element: <Callback />,
  },
]);

const App = () => {
  return (
    <Auth0Provider
      domain={authConfig.domain}
      clientId={authConfig.clientId}
      authorizationParams={{
        redirect_uri: authConfig.redirectUri,
        audience: authConfig.audience,
      }}
    >
      <RouterProvider router={router} />
    </Auth0Provider>
  );
};

export default App;
