import React from 'react';
//import logo from './logo.svg';
import './App.css';
import { BrowserRouter, Route, Routes } from "react-router-dom";
import ListweatherComponent from './components/ListweatherComponent';
import HeaderComponent from './components/HeaderComponent';
import FooterComponent from './components/FooterComponent';
import CreateweatherComponent from './components/CreateweatherComponent';
//import UpdateweatherComponent from './components/UpdateweatherComponent';
import ViewweatherComponent from './components/ViewweatherComponent';

import { ReactKeycloakProvider } from "@react-keycloak/web";
import keycloak from "./Keycloak";
import Nav from "./components/Nav";
import PrivateRoute from "./helpers/PrivateRoute";

function App() {
  return (
    <div>
      <ReactKeycloakProvider authClient={keycloak}>
      <HeaderComponent />
       <BrowserRouter>
         <Routes>
         <div className="container">
           <Route
             path="/secured"
             element={
               <PrivateRoute>
                 <ListweatherComponent />
               </PrivateRoute>
             }
           />
           </div>
         </Routes>
       </BrowserRouter>
        {/*
        <Router>
              <HeaderComponent>
              
              </HeaderComponent>
              
                <div className="container">
                    <Switch> 
                          <Route path = "/secured" element={
               <PrivateRoute>
                 <ListweatherComponent />
               </PrivateRoute>
             }></Route>
                          <Route path = "/weathers" element={
               <PrivateRoute>
                 <ListweatherComponent />
               </PrivateRoute>
             }></Route>
                          <Route path = "/add-weather/:id" element={
               <PrivateRoute>
                 <CreateweatherComponent />
               </PrivateRoute>
             }></Route>
                          <Route path = "/view-weather/:id" element={
               <PrivateRoute>
                 <ViewweatherComponent />
               </PrivateRoute>
             }></Route>
                          <Route path = "/update-weather/:id" component = {UpdateweatherComponent}></Route>
                    </Switch>
                </div>
              <FooterComponent />
        </Router>
      */}
        </ReactKeycloakProvider>
    </div>
    
  );
}

export default App;
