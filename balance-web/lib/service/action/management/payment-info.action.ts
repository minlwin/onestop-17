import { PaymentInfoForm } from "@/lib/types";

import * as client from "@/lib/service/client/management/payment-info.client"
import { redirect } from "next/navigation";

export async function create(form : PaymentInfoForm) {
    const response = await client.create(form)
    redirect(`/management/master/payments/${response.result}`)   
}

export async function update(id : any, form : PaymentInfoForm) {
    const response = await client.update(id, form)
    redirect(`/management/master/payments/${response.result}`)   
}