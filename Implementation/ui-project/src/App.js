import React from "react";
import Login from './Component/Login/Login'
import Home from './Component/Home/Home'
import Dashboard from './Component/DashBoard/Body'
import styles from './Component/DashBoard/static/styles.module.css'
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link,
  useRouteMatch,
  useParams
} from "react-router-dom";

export default function App() {
  return (
    <Router>
      <div className = {styles.maxHeigh}>
        <Switch>
          <Route path="/Login">
            <Login />
          </Route>
          <Route path="/dashboard">
            <Dashboard />
          </Route>
          <Route path="/">
            <Home />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}