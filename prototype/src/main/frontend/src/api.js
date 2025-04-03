const endpoint = "http://localhost:8080";

export const mockApiLogin = async (username, password) => {
  const response = await fetch(
    "https://triptop-identity.wiremockapi.cloud/login",
    {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify({ username, password }),
    }
  );

  if (!response.ok) {
    throw new Error("Invalid credentials");
  }

  const { token } = await response.json();

  const newToken = await exchangeToken("mockapi", token["value"], [
    username,
    "triptop",
  ]);
  return newToken
}

export const exchangeToken = async (name, token, options) => {
    const url = `${endpoint}/authenticate`;
  
    const bodyData = {
      name,
      token,
      options,
    };
  
    try {
      const response = await fetch(url, {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify(bodyData),
      });
  
      if (!response.ok) {
        throw new Error(`HTTP error! status: ${response.status}`);
      }
  

const body = await response.text();

    return body

    } catch (error) {
      console.error('Error posting data:', error);
    }
  };

export const accessProtected = async (token, idp) => {
  const url = `${endpoint}/protected`;

  try {
    const response = await fetch(url, {
      method: "GET",
      headers: {
        Authorization: `Bearer ${token}`,
        "X-Authorization": idp,
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error(`HTTP error! Status: ${response.status}`);
    }

    const data = await response.text();
    alert(data);
  } catch (error) {
    alert(error);
    console.error("Error:", error);
  }
};