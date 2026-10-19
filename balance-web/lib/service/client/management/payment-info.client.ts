import { ModificationResult, PaymentInfo, PaymentInfoForm } from "@/lib/types";
import { secureRequest } from "..";

const BASE_API = "management/master/payments"

export async function findAll() : Promise<PaymentInfo[]> {
    return await secureRequest(BASE_API)
}

export async function findById(id : any) : Promise<PaymentInfo> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function create(form : PaymentInfoForm) : Promise<ModificationResult<number>> {
    return await secureRequest(BASE_API, form, {method : "POST"})
}

export async function update(id : any, form : PaymentInfoForm) : Promise<ModificationResult<number>> {
    return await secureRequest(`${BASE_API}/${id}`, form, { method : "PUT" })
}
