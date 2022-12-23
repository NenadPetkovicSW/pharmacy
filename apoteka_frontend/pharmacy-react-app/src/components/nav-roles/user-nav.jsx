import '../../App.css';
import React, { Component } from "react";
import { Link} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useHistory } from "react-router-dom";

export default function UserSite() {
  const history = useHistory();

  function logingOut()
  {
    localStorage.clear();
    history.push("/");
    history.go(0);
  }

    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/" className="navbar-brand">
            Pharmacy
          </a>
          <div className="navbar-nav mr-auto">
          <li className="nav-item">
              <Link to={"/myProfile"} className="nav-link">
                My Profile
              </Link>
          </li>
          <li className="nav-item">
              <Link to={"/MyPhaSearch"} className="nav-link">
                Pharmacies
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/MyMedSearch"} className="nav-link">
                Medication
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/myHome"} className="nav-link">
                Home Page
              </Link>
            </li>
            <li className="nav-item">
              <Link onClick={logingOut} className="nav-link">
                Log out
              </Link>
            </li>
          </div>
        </nav>
    );
  }