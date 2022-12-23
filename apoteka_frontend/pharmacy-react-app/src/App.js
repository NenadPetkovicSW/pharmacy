import logo from './logo.svg';
import './App.css';

import React, { Component } from "react";
import { Link, Switch, Route } from "react-router-dom";
import "bootstrap/dist/css/bootstrap.min.css";
import AddPharmacy from './components/pharmacy/add-pharmacy';
import PharmacyTable from './components/pharmacy/show-all-pharmacies';
import 'bootstrap/dist/css/bootstrap.min.css';
import ShowPharmacy from './components/pharmacy/show-pharmacy';
import AddPharmacist from './components/pharmacists/add-pharmacist';
import PharmacyHome from './components/pharmacy/pharmacy-home';
import PharmacistsTable from './components/pharmacists/show-all-pharmacists';
import PharmacistsHome from './components/pharmacists/pharmacists-home';
import ShowPharmacist from './components/pharmacists/show-pharmacist';
import AddDermatologist from './components/dermatologists/add-dermatologist';
import DermatologistsTable from './components/dermatologists/show-all-dermatologists';
import DermatologistsHome from './components/dermatologists/dermatologists-home';
import ShowDermatologist from './components/dermatologists/show-dermatologist';
import AddMedication from './components/medication/add-medication';
import MedicationTable from './components/medication/show-all-medications';
import ShowProfile from './components/user/show-user-profile';
import ShowProfiles from './components/user/show-user-profiles';
import HomePage from './components/user/home-page';
import AddUser from './components/user/add-user';
import ShowMedication from './components/medication/show-medication';
import EditUser from './components/user/edit-user';
import ShowAllergies from './components/allergies/show-allergies';
import AddAllergies from './components/allergies/add-allergies';
import AddPromotion from './components/actions-promotions/add-promotion';
import AddAction from './components/actions-promotions/add-action';
import AddScore from './components/scores/add-score';
import ShowAllAppointments from './components/appointments/show-all-dermatologist-appointments'
import AddAdminDA from './components/appointments/add-appointments-admin'
import Login from './components/login/login'
import NavAdmin from './components/nav-roles/admin'
import NavUser from './components/nav-roles/user-nav'
import NavUnrg from './components/nav-roles/unreg'
import WellcomePage from './components/wellcome/wellcome-page'
import Register from './components/login/register'
import WelcomePhaSearch from './components/wellcome/pharmacy-serach'
import WelcomeMedSearch from './components/wellcome/medication-search'
import AdminMedicationSearch from './components/pharmacy-admin/medication-search-adminn';
import AdminSearchPage from './components/pharmacy-admin/search-page';
import DermatologistsSearch from './components/pharmacy-admin/dermatologist-search-admin';
import MyProfile from './components/patient/myProfile'
import MyPhaSearch from './components/patient/my-pharmacy-serach'
import MyMedSearch from './components/patient/my-medication-search'
import MyHomePage from './components/patient/my-home-page'
import MyProfileAdmin from './components/pharmacy-admin/my-profile'
import ShowUserPharmacy from './components/patient/show-pharmacy'
import ShowFreeDerAppointmentsByPhar from './components/patient/pharmacy-dermatologist-appointments'
import ShowDerAppointmentsByUser from './components/patient/cancel-dermatologist-appointments'
import PromotionActionHome from './components/actions-promotions/promotion-action-home-page';
import PromotionsTable from './components/actions-promotions/show-promotions-table';
import ActionsTable from './components/actions-promotions/show-actions-table';
import FreePharmacySearch from './components/patient/free-pharmacy-serach';
import FreePharmacyPharmacist from './components/patient/free-pharmacy-pharmacist';
import PharmacistAppointmentMake from './components/patient/pharmacists-pharmacist-appointments'
import ShowPharAppointmentsByUser from './components/patient/cancel-pharmacist-appointments'
import SearchHomePatient  from './components/patient/search-home';
import DermatologistsSearchPatient from './components/patient/search-dermatologists';
import PharmacistsSearchPatient from './components/patient/search-pharmacists';
import PharmacistsSearchAdmin  from './components/pharmacy-admin/search-pharmacists-admin';
import AddWorkTimeDermatologists from './components/dermatologists/add-dermatologist-work-time';
import EditPassword from './components/pharmacy-admin/edit-password';
import OrderPage  from './components/medication/orders/order-page';
import ShowMedicationInUser from './components/patient/show-medication';
import MedicationInPharmacy from './components/patient/pharmacy-medication-search'
import CancelOrder from './components/patient/cancel-order'
import PaidLeaveHomePage from './components/pharmacy-admin/paid-leave/show-all-paid-leaves';
import ShowPaidLeave from './components/pharmacy-admin/paid-leave/paid-leave';
import VacationsHomePage from './components/pharmacy-admin/vacations/show-all-vacations';
import ShowVacation from './components/pharmacy-admin/vacations/vacation';
import ScoreDermatologist from './components/patient/score-dermatologists';
import ScorePharmacist from './components/patient/score-pharmacists';
import MonthlyReports from './components/pharmacy-admin/reports/monthly-reports/show-monthly-appointments';
import QuarterlyReports from './components/pharmacy-admin/reports/quarterly-reports/show-quarterly-appointments';
import YearlyReports from './components/pharmacy-admin/reports/yearly-reports/show-yearly-appointments';
import YearlyReportsMedication from './components/pharmacy-admin/reports-medications/yearly-reports/show-yearly-appointments';
import MonthlyReportsMedication from './components/pharmacy-admin/reports-medications/monthly-reports/show-monthly-appointments';
import QuarterlyReportsMedication from './components/pharmacy-admin/reports-medications/quarterly-reports/show-quarterly-appointments';
import ReportsHome from './components/pharmacy-admin/reports-home';
import ScoreMedication from './components/patient/score-medication';
import ScorePharmacy from './components/patient/score-pharmacy';
import EditAllergiesPatient from './components/patient/add_allergies';

function App() {

  //let pomocna = JSON.parse(localStorage.getItem('LogedUser'));
  //alert(pomocna.username);

  const role =  localStorage.getItem('LogedRole');


  return (
    
    //podeli na komponente jos
    <div>
      
      { role == "USER"
        ? <NavUser /> 
        : null
      }
      { role == "ADMIN"
        ? <NavAdmin /> 
        : null
      }
      { role != "ADMIN" && role != "USER"
        ? <NavUnrg /> 
        : null
      }
        
        <div className="container mt-3">
          <Switch>
            <Route exact path={"/"} component={WellcomePage} />
            <Route exact path={"/pharmacies-home"} component={PharmacyHome} />
            <Route exact path="/pharmacies" component={PharmacyTable} />
            <Route exact path="/pharmacies/add" component={AddPharmacy} />
            <Route exact path="/reports" component={ReportsHome}/>
            <Route exact path="/pharmacists" component={PharmacistsTable} />
            <Route exact path="/pharmacists/add" component={AddPharmacist} />
            <Route exact path="/pharmacists-home" component={PharmacistsHome}></Route>
            <Route exact path="/dermatologists" component={DermatologistsTable} />
            <Route exact path="/dermatologists/add" component={AddDermatologist} />
            <Route exact path="/dermatologists-home" component={DermatologistsHome}></Route>
            <Route exact path="/medications" component={() => <MedicationTable id={null} />} />
            <Route exact path="/medications/add" component={AddMedication} />
            <Route exact path="/orders" component={OrderPage} />
            <Route exact path="/paidLeaves" component={PaidLeaveHomePage} />
            <Route path="/paidLeaves/:id" component={ShowPaidLeave} />
            <Route path="/vacations/:id" component={ShowVacation} />
            <Route exact path="/vacations" component={VacationsHomePage} />
            <Route path="/medications/pharmacy/:pharmacyId" component={() => <MedicationTable id={null} />} />
            <Route path="/promotions/pharmacy/:pharmacyId" component={PromotionsTable} />
            <Route path="/actions/pharmacy/:pharmacyId" component={ActionsTable} />
            <Route path="/pharmacies/:id" component={ShowPharmacy} />
            <Route path="/medications/:id" component={ShowMedication} />
            <Route path="/pharmacists/:id" component={ShowPharmacist} />
            <Route path="/dermatologists/:id" component={ShowDermatologist} />
            <Route exact path="/profile" component={ShowProfiles} />
            <Route exact path="/reports/monthly" component={MonthlyReports} />
            <Route exact path="/reports/quarterly" component={QuarterlyReports} />
            <Route exact path="/reports/yearly" component={YearlyReports} />
            <Route exact path="/reports/medication/yearly" component={YearlyReportsMedication} />
            <Route exact path="/reports/medication/quarterly" component={QuarterlyReportsMedication} />
            <Route exact path="/reports/medication/monthly" component={MonthlyReportsMedication} />
            <Route exact path="/home" component={HomePage} />
            <Route exact path="/myHome" component={MyHomePage} />
            <Route exact path="/user/add" component={AddUser} />
            <Route path="/profile/:id" component={ShowProfile} />
            <Route path="/profile-edit/:id" component={EditUser} />
            <Route path="/password-edit/:id" component={EditPassword} />
            <Route exact path="/allergies" component={ShowAllergies} />
            <Route exact path="/allergies-add" component={AddAllergies} />
            <Route exact path="/score-add" component={AddScore} />
            <Route exact path="/dermatologists-appointments" component={ShowAllAppointments} />
            <Route exact path="/login" component={Login} />
            <Route exact path="/register" component={Register} />
            <Route exact path="/unregPhaSearch" component={WelcomePhaSearch} />
            <Route exact path="/unregMedSearch" component={WelcomeMedSearch} />
            <Route exact path="/myPhaSearch" component={MyPhaSearch} />
            <Route exact path="/myMedSearch" component={MyMedSearch} />
            <Route exact path="/patient/search" component={SearchHomePatient} />
            <Route exact path="/patient/search/dermatologists" component={DermatologistsSearchPatient} />
            <Route exact path="/patient/search/pharmacists" component={PharmacistsSearchPatient} />
            <Route exact path="/add-promotion" component={AddPromotion} />
            <Route exact path="/add-action" component={AddAction} />
            <Route exact path={"/promotions-actions-home"} component={PromotionActionHome} />
            <Route path="/admin-add-appointment-dermatologist/:id" component={AddAdminDA} />
            <Route path="/admin-add-worktime-dermatologist/:id" component={AddWorkTimeDermatologists} />
            <Route exact path="/myProfile" component={MyProfile} />
            <Route exact path="/admin/search/medication" component={AdminMedicationSearch} />
            <Route exact path="/admin/search/dermatologists" component={DermatologistsSearch} />
            <Route exact path="/admin/search/pharmacists" component={PharmacistsSearchAdmin} />
            <Route exact path="/admin/search/" component={AdminSearchPage} />
            <Route exact path="/my-profile-admin" component={MyProfileAdmin} />
            <Route path="/pharmaciesUser/:id" component={ShowUserPharmacy} />
            <Route exact path="/dermatologists-appointments/:id" component={ShowFreeDerAppointmentsByPhar} />
            <Route exact path="/dermatologists-appointments-cancel/" component={ShowDerAppointmentsByUser} />
            <Route exact path="/pharmacist-appointments-search/" component={FreePharmacySearch} />
            <Route exact path="/free-pharmacy-pharmacist/:id" component={FreePharmacyPharmacist} />
            <Route path="/pharmacist-appointments/:id" component={PharmacistAppointmentMake} />
            <Route exact path="/pharmacist-appointments-cancel/" component={ShowPharAppointmentsByUser} />
            <Route path="/medication-user/:id" component={ShowMedicationInUser} />
            <Route path="/medications-in-pharmacy/:pharmacyId" component={MedicationInPharmacy} />
            <Route exact path="/cancel-user-order/" component={CancelOrder} />
            <Route exact path="/scoreDermatologist/" component={ScoreDermatologist} />
            <Route exact path="/scorePharmacist/" component={ScorePharmacist} />
            <Route exact path="/scoreMedication/" component={ScoreMedication} />
            <Route exact path="/scorePharmacy/" component={ScorePharmacy} />
            <Route exact path="/profile-edit-patient-allergies/" component={EditAllergiesPatient} />
          </Switch>
        </div>
    </div>
  );
}

export default App;
