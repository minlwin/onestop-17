import { Card, CardContent, CardHeader, CardTitle } from "@/components/ui/card"
import ManagementTemplate from "@/components/widgets/pages/management-template"
import * as client from "@/lib/service/client/management/registration.client"
import { UserPlus } from "lucide-react"
import ApproveButton from "./_client/approve-button"
import SectionTitle from "@/components/widgets/section-title"
import Info from "@/components/widgets/info"
import AuditSection from "@/components/widgets/audit-section"

export default async function RegistrationDetailsPage(props : PageProps<'/management/registrations/[id]'>) {
    const { id } = await props.params
    const details = await client.findById(id)

    return (
        <ManagementTemplate page="Details" links={[
            {name : "Registrations", route : "/management/registrations"}
        ]}>
            <Card>
                <CardHeader>
                    <CardTitle className="flex justify-between">
                        <h3 className="flex items-center gap-2">
                            <UserPlus size={22} />
                            <span className="text-xl">{details.partnerName}</span>
                        </h3>

                        {details.status === 'Pending' &&
                            <ApproveButton id={id} />
                        }
                    </CardTitle>
                </CardHeader>

                <CardContent className="space-y-6">
                    <section className="space-y-4">
                        <SectionTitle title="Registration Status" />

                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Status" value={details.status} />
                            <Info label="Registered At" value={details.registeredAt} />
                            <Info label="Approved At" value={details.approvedAt || "Not Yet"} />
                        </div>
                    </section>

                    <section className="space-y-4">
                        <SectionTitle title="Partner Information" />

                        <div className="grid grid-cols-4 gap-4">
                            <Info label="Company" value={details.company} />
                            <Info label="Position" value={details.position} />
                            <Info label="Phone" value={details.phone} />
                            <Info label="Email" value={details.email} />
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