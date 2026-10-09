import { SubscriptionPlanForm } from "@/lib/types";

import * as client from "@/lib/service/client/management/subscription-plan.client"
import { redirect } from "next/navigation";

export async function create(form : SubscriptionPlanForm) {
    const response = await client.create(form)
    redirect(`/management/master/plans/${response.result}`)   
}

export async function update(id : any, form : SubscriptionPlanForm) {
    const response = await client.update(id, form)
    redirect(`/management/master/plans/${response.result}`)   
}