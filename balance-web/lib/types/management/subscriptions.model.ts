import z from "zod"
import { AuditInfo } from ".."

export type SubscriptionStatus = "Pending" | "Approved" | "Expired"

export interface SubscriptionSearch {
    status?: SubscriptionStatus
    appliedFrom? : string
    appliedTo? : string
    keyword? : string
}

export interface SubscriptionListItem {
    id: string
    partnerName: string
    phone : string
    email : string
    company : string
    planId : number
    planName : string
    months : number
    amount : number
    paidAt : string
    status : SubscriptionStatus
    appliedAt : string
    approvedAt : string
    subscribedAt : string
}

export type SubscriptionDetails = SubscriptionListItem 
    & {
        paymentProvider : string
        accountNo : string
        accountName : string
        pamentSlip : string
    } & AuditInfo

export const subscriptionStatusSchema = z.object({
    status : z.enum(["Pending", "Approved", "Expired"]).nonoptional("Please select status."),
    remark : z.string()
})

export type SubscriptionStatusForm = z.infer<typeof subscriptionStatusSchema>