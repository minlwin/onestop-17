import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import AuditSection from "@/components/widgets/audit-section"
import Info from "@/components/widgets/info"
import ManagementTemplate from "@/components/widgets/pages/management-template"
import SectionTitle from "@/components/widgets/section-title"
import * as client from "@/lib/service/client/management/subscription.client"
import { ShieldCheck } from "lucide-react"
import UpdateStatusButton from "./_client/update-status-button"
import ShowSlipButton from "./_client/show-slip-button"

export default async function SubscriptionDetailsPage(props : PageProps<'/management/subscriptions/[id]'>) {
    const { id } = await props.params
    const details = await client.findById(id)

    return (
        <ManagementTemplate page="Details" links={[
            {name : "Subscriptions", route : "/management/subscriptions"}
        ]}>
            <Card>
                <CardHeader>
                    <CardTitle className="flex justify-between">
                        <h3 className="flex gap-2 items-center">
                            <ShieldCheck size={20} /> 
                            <span className="text-xl">Subscription</span>
                        </h3>

                        <nav className="space-x-2">
                            <ShowSlipButton slip={details.pamentSlip} />
                            {details.status === 'Pending' && 
                                <UpdateStatusButton id={id} />
                            }
                        </nav>
                    </CardTitle>
                </CardHeader>
                <CardContent className="space-y-6">
                    <section className="space-y-4">
                        <SectionTitle title="Subscription Plan" />
                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Plan" value={details.planName} />
                            <Info label="Months" value={`${details.months} Months`} />
                            <Info label="Subscribe At" value={details.subscribedAt} />
                        </div>
                    </section>

                    <section className="space-y-4">
                        <SectionTitle title="Subscription Status" />

                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Status" value={details.status} />
                            <Info label="Applied At" value={details.appliedAt} />
                            <Info label="Approved At" value={details.approvedAt || "Not Yet"} />
                        </div>
                    </section>

                    <section className="space-y-4">
                        <SectionTitle title="Payment Infomation" />

                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Provider" value={details.paymentProvider} />
                            <Info label="Account" value={details.accountName} />
                            <Info label="Amount" value={`${details.amount.toLocaleString()} MMK`} />
                            <Info label="Paid At" value={details.paidAt} />
                        </div>
                    </section>

                    <AuditSection 
                        createdBy={details.createdBy} 
                        createdAt={details.createdAt}
                        modifiedBy={details.modifiedBy}
                        modifiedAt={details.modifiedAt} />
                    

                </CardContent>
            </Card>
        </ManagementTemplate>
    )
}