import { useKeycloak } from "@react-keycloak/web";

const PrivateRoute = ({ children }) => {
 const { keycloak } = useKeycloak();

 const isLoggedIn = keycloak.authenticated;

 console.log("isLoggedIn",isLoggedIn);
    if(isLoggedIn == true){
        localStorage.setItem('token', keycloak.token);  
    }
 return isLoggedIn ? children : null;
};

export default PrivateRoute;