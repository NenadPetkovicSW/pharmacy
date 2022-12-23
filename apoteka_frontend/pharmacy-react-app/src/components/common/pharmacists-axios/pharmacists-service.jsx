import http from "../http-common";

class PharmacistsDataService {
    getAll() {
        return http.get("/pharmacistsData");
    }

    getAllSearch(name,lastname, pharmacyid){
        return http.get(`/pharmacistsData/search/${name}/${lastname}/${pharmacyid}`);
    }

    getScorable(idUser)
    {
        return http.get(`/pharmacistsData/score/${idUser}`);
    }
    getFreePharmacist(pharmacyId)
    {
        return http.get(`/pharmacistsData/free/${pharmacyId}`);
    }
    
    get(id) {
        return http.get(`/pharmacistsData/${id}`);
    }

    create(data) {
        return http.post("/pharmacistsData", data);
    }

    update(id, data) {
        return http.put(`/pharmacistsData/${id}`, data);
    }

    delete(id) {
        return http.delete(`/pharmacistsData/${id}`);
    }
}

export default new PharmacistsDataService();