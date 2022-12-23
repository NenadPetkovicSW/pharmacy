import http from "../http-common";

class DermatologistsAppointmentDataService {
  getAll() {
    return http.get("/pharmacistsAppointmentData");
  }

  
  get(id) {
    return http.get(`/pharmacistsAppointmentData/${id}`);
  }

  getAppointmentsByUser(userId)
  {
    return http.get(`/pharmacistsAppointmentData/byUser/${userId}`);
  }
  
  getFree(pharmacistId) {
    return http.get(`/pharmacistsAppointmentData/free/${pharmacistId}`);
  }

  create(data) {
    return http.post("/pharmacistsAppointmentData", data);
  }

  update(id, data) {
    return http.put(`/pharmacistsAppointmentData/${id}`, data);
  }

  makeAppointment(idApp, idUser)
  {
    return http.put(`/pharmacistsAppointmentData/${idApp}/${idUser}`);
  }

  cancelAppointment(idApp)
  {
    return http.put(`/pharmacistsAppointmentData/cancel/${idApp}`);
  }

  delete(id) {
    return http.delete(`/pharmacistsAppointmentData/${id}`);
  }
}

export default new DermatologistsAppointmentDataService();