import '../../App.css';
import React, { Component } from "react";
import { Link} from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import 'bootstrap/dist/css/bootstrap.min.css';
import { useHistory } from "react-router-dom";

export default function AdminSite() {
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
              <Link to={"/pharmacies-home"} className="nav-link">
                Pharmacies home
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/myPhaSearch"} className="nav-link">
                Pharmacies
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/pharmacists-home"} className="nav-link">
                Pharmacists
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/dermatologists-home"} className="nav-link">
                Dermatologists
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/promotions-actions-home"} className="nav-link">
                Promotions/Actions
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/admin/search/"} className="nav-link">
                Search
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/paidLeaves"} className="nav-link">
                Paid Leaves
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/vacations"} className="nav-link">
                Vacations
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/home"} className="nav-link">
                Home Page
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/reports"} className="nav-link">
                Reports
              </Link>
            </li>
            <li className="nav-item">
              <Link to={"/my-profile-admin"} className="nav-link">
                My Profile
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