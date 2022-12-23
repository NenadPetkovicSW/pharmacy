import http from "../http-common";

class DermatologistsAppointmentDataService {
  getAll() {
    return http.get("/dermatologistsAppointmentData");
  }

  
  get(id) {
    return http.get(`/dermatologistsAppointmentData/${id}`);
  }

  create(data) {
    return http.post("/dermatologistsAppointmentData", data);
  }

  update(id, data) {
    return http.put(`/dermatologistsAppointmentData/${id}`, data);
  }

  makeAppointment(idApp, idUser)
  {
    return http.put(`/dermatologistsAppointmentData/${idApp}/${idUser}`);
  }

  cancelAppointment(idApp)
  {
    return http.put(`/dermatologistsAppointmentData/cancel/${idApp}`);
  }

  getFreeAppointmentsInPharmacy(pharmacyId)
  {
    return http.get(`/dermatologistsAppointmentData/byPharmacy/${pharmacyId}`);
  }

  getAppointmentsByUser(userId)
  {
    return http.get(`/dermatologistsAppointmentData/byUser/${userId}`);
  }

  delete(id) {
    return http.delete(`/dermatologistsAppointmentData/${id}`);
  }
}

export default new DermatologistsAppointmentDataService();