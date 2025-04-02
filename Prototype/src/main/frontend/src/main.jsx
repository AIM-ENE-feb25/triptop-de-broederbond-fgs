import { StrictMode } from "react";
import { createRoot } from "react-dom/client";
import { Auth0Provider } from "@auth0/auth0-react";

import { createBrowserRouter, RouterProvider } from "react-router";

import App from "./App.jsx";
import LoginButton from "./login.jsx";
import LogoutButton from "./logout.jsx";
import Profile from "./profile.jsx";

const router = createBrowserRouter([
  {
    path: "/",
    element: <App />, // <-- Use JSX here
  },
  {
    path: "/login",
    element: <LoginButton />, // <-- Use JSX here
  },
  {
    path: "/logout",
    element: <LogoutButton />, // <-- Use JSX here
  },
  {
    path: "/profile",
    element: <Profile />, // <-- Use JSX here
  },
]);

createRoot(document.getElementById("root")).render(
  <StrictMode>
    <Auth0Provider
      domain="dev-1yukta3ze7ly4fnr.eu.auth0.com"
      clientId="NCTqEhkXW0tMY13FuVVNoG83WSmSuP9O"
      authorizationParams={{
        redirect_uri: window.location.origin,
        audience: "https://dev-1yukta3ze7ly4fnr.eu.auth0.com/api/v2/",
      }}
      cacheLocation="localstorage"
    >
      <RouterProvider router={router} />
    </Auth0Provider>
  </StrictMode>
);
