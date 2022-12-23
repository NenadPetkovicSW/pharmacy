import '../../App.css';
import React, { Component } from "react";
import { Link} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/css/bootstrap.min.css';

export default function UnregSite() {
    return (
        <nav className="navbar navbar-expand navbar-dark bg-dark">
          <a href="/" className="navbar-brand">
            Pharmacy
          </a>
          <div className="navbar-nav mr-auto">
            <li className="nav-item">
              <Link to={"/unregPhaSearch"} className="nav-link">
                Pharmacies
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/unregMedSearch"} className="nav-link">
                Medication
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/register"} className="nav-link">
                Register
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/login"} className="nav-link">
                Login
              </Link>
            </li>
          </div>
        </nav>
    );
  }